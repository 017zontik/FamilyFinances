package com.zontik.groshiky.service;

import com.zontik.groshiky.dao.IAccountDao;
import com.zontik.groshiky.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountService implements IAccountService{

    private final IAccountDao accountDao;

    @Autowired
    public AccountService(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    @Override
    public Account addAccount(Account account) {
        account.setBalance(0f);
        return accountDao.save(account);
    }

    @Override
    public Account getAccountByName(String name) {
        return accountDao.findByName(name);
    }


}
