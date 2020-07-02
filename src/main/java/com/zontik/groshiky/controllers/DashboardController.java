package com.zontik.groshiky.controllers;

import com.zontik.groshiky.model.Account;
import com.zontik.groshiky.service.IAccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller("/dashboard")
public class DashboardController extends BaseController {

    private final  IAccountService accountService;

    @Autowired
    public DashboardController(IAccountService accountService) {
        this.accountService = accountService;
    }

    @GetMapping("/addAccount")
    public String  addAccountForm( Model model) {

        model.addAttribute("account", new Account());

       return "dashboard";
    }

    @PostMapping("/addAccount")
    public String  addAccount( @ModelAttribute("account") Account account){

            accountService.addAccount(account);
            return "redirect:dashboard";

    }

}
