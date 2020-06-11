package com.zontik.groshiky.controllers;

import com.zontik.groshiky.model.User;
import com.zontik.groshiky.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class IndexController {

    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        return "index";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user") User user) {
        if (userService.findByLogin(user.getLogin()) != null) {
           return "registration";
        }
        userService.createUser(user);
        return "dashboard";
    }
}
