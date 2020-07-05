package com.zontik.groshiky.controllers;

import com.zontik.groshiky.model.Account;
import com.zontik.groshiky.model.AccountResponse;
import com.zontik.groshiky.service.IAccountService;
import com.zontik.groshiky.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller("/dashboard")
public class AccountController extends BaseController {

    private final IAccountService accountService;
    private final IUserService userService;

    @Autowired
    public AccountController(IAccountService accountService, IUserService userService) {
        this.accountService = accountService;
        this.userService = userService;
    }


    @PostMapping(value = "/addAccount", produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    public AccountResponse addAccount(Account account) {
        Account newAccount = new Account();
        newAccount.setName(account.getName());
        account.setUser(userService.findUserById(getUserId()));
        if(accountService.getAccountByName(account.getName())!=null){
           String message ="This account name is already exists";
           return new AccountResponse(message);
        }
        accountService.addAccount(account);
        return new AccountResponse(newAccount);

    }
  }
