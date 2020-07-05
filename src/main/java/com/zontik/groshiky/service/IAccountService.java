package com.zontik.groshiky.service;

import com.zontik.groshiky.model.Account;

public interface IAccountService {
    Account addAccount(Account account);
    Account getAccountByName(String name);
}
