package com.zontik.groshiky.service;

import com.zontik.groshiky.model.Account;
import org.springframework.security.core.userdetails.User;

public interface IAccountService {
    Account addAccount(Account account);


}
