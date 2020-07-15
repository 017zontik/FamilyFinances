package com.zontik.groshiky.service;

import com.zontik.groshiky.repository.AccountRepository;
import com.zontik.groshiky.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService{

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account addAccount(Account account) {
        account.setBalance(0f);
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountByName(String name) {
        return accountRepository.findByName(name);
    }


}
