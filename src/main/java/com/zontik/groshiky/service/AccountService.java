package com.zontik.groshiky.service;

import com.zontik.groshiky.dao.IAccountDao;
import com.zontik.groshiky.dao.IUserDao;
import com.zontik.groshiky.model.Account;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AccountService implements IAccountService{

    private final IAccountDao accountDao;

    @Autowired
    public AccountService(IAccountDao accountDao) {
        this.accountDao = accountDao;

    }

    @Override
    public Account addAccount(Account account) {
        if(accountDao.existsByName(account.getName())){
            log.info("Account name - {} is already exists ", account.getName());
        }
        account.setBalance(0f);
       return accountDao.save(account);
    }


}
