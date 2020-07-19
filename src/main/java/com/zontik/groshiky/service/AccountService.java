package com.zontik.groshiky.service;

import com.zontik.groshiky.repository.AccountRepository;
import com.zontik.groshiky.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService{

    private final AccountRepository accountRepository;
    private final static float DEFAULT_BALANCE = 0f;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account addAccount(Account account) {
        account.setBalance(DEFAULT_BALANCE);
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountByName(String name) {
        return accountRepository.findByName(name);
    }

    @Override
    public Account findAccountById(Integer id) {
        return accountRepository.findAllById(id);
    }
}
