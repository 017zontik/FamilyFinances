package com.zontik.groshiky.controllers;

import com.zontik.groshiky.model.User;
import com.zontik.groshiky.service.IAccountService;
import com.zontik.groshiky.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
public class IndexController {

    private final IAccountService accountService;
    private final IUserService userService;

    @Autowired
    public IndexController(IUserService userService, IAccountService accountService) {
        this.userService = userService;
        this.accountService = accountService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@RequestParam(value = "error", required = false) String error, Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("error", error != null);
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user, Model model) {
        if (userService.findByLogin(user.getLogin()) != null) {
            model.addAttribute("error", "This login name is already taken. Please enter another one.");
            return "registration";
        }
        userService.createUser(user);
        return "dashboard";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model) {
        User user = userService.findUserById(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getId());
        model.addAttribute("accounts", user.getAccounts());
        return "dashboard";


    }

}
