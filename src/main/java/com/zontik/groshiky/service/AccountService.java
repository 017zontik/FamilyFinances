package com.zontik.groshiky.service;

import com.zontik.groshiky.repository.AccountRepository;
import com.zontik.groshiky.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService implements IAccountService{

    private final AccountRepository accountRepository;
    private final static double DEFAULT_BALANCE = 0d;

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
    public Account getAccountByName(String name, Integer id) {
        return accountRepository.findByNameAndUserId(name, id);
    }

    @Override
    public Account findAccountById(Integer id) {
        return accountRepository.findAccountById(id);
    }
}
