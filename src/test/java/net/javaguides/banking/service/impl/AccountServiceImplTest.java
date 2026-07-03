package net.javaguides.banking.service.impl;

import net.javaguides.banking.dto.AccountDto;
import net.javaguides.banking.entity.Account;
import net.javaguides.banking.repository.AccountRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @Mock
    private AccountRepository accountRepository;

    @InjectMocks
    private AccountServiceImpl accountService;

    @Test
    void createAccount_ShouldReturnSavedAccount() {
        AccountDto accountDto = new AccountDto(null, "Paavan Ghuman", 1000.0);
        Account savedAccount = new Account(1L, "Paavan Ghuman", 1000.0);

        when(accountRepository.save(any(Account.class))).thenReturn(savedAccount);

        AccountDto result = accountService.createAccount(accountDto);

        assertEquals(1L, result.getId());
        assertEquals("Paavan Ghuman", result.getAccountHolderName());
        assertEquals(1000.0, result.getBalance());
    }

    @Test
    void getAccountById_ShouldReturnAccount() {
        Account account = new Account(1L, "Paavan Ghuman", 1000.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        AccountDto result = accountService.getAccountById(1L);

        assertEquals(1L, result.getId());
        assertEquals("Paavan Ghuman", result.getAccountHolderName());
    }

    @Test
    void deposit_ShouldIncreaseBalance() {
        Account account = new Account(1L, "Paavan Ghuman", 1000.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountDto result = accountService.deposit(1L, 500.0);

        assertEquals(1500.0, result.getBalance());
    }

    @Test
    void withdraw_ShouldDecreaseBalance() {
        Account account = new Account(1L, "Paavan Ghuman", 1000.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));
        when(accountRepository.save(any(Account.class))).thenReturn(account);

        AccountDto result = accountService.withdraw(1L, 300.0);

        assertEquals(700.0, result.getBalance());
    }

    @Test
    void withdraw_ShouldThrowException_WhenInsufficientAmount() {
        Account account = new Account(1L, "Paavan Ghuman", 100.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        RuntimeException exception = assertThrows(RuntimeException.class, () ->
                accountService.withdraw(1L, 300.0)
        );

        assertEquals("Insufficient Amount", exception.getMessage());
        verify(accountRepository, never()).save(any(Account.class));
    }

    @Test
    void getAllAccounts_ShouldReturnListOfAccounts() {
        Account account1 = new Account(1L, "Paavan Ghuman", 1000.0);
        Account account2 = new Account(2L, "John Doe", 500.0);

        when(accountRepository.findAll()).thenReturn(Arrays.asList(account1, account2));

        List<AccountDto> result = accountService.getAllAccounts();

        assertEquals(2, result.size());
    }

    @Test
    void deleteAccount_ShouldDeleteAccount() {
        Account account = new Account(1L, "Paavan Ghuman", 1000.0);

        when(accountRepository.findById(1L)).thenReturn(Optional.of(account));

        accountService.deleteAccount(1L);

        verify(accountRepository, times(1)).deleteById(1L);
    }
}