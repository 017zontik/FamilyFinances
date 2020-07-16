package com.zontik.groshiky.controllers;

import com.zontik.groshiky.model.Account;
import com.zontik.groshiky.model.AccountModel;
import com.zontik.groshiky.model.AccountResponse;
import com.zontik.groshiky.service.IAccountService;
import com.zontik.groshiky.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        account.setUser(userService.findUserById(getUserId()));
        if(accountService.getAccountByName(account.getName())!=null){
           String message =String.format("The account \"%s\" already exists", account.getName());
           return new AccountResponse(message);
        }
        accountService.addAccount(account);
        AccountModel newAccount = new AccountModel(account);
        return new AccountResponse(newAccount);
    }
  }
