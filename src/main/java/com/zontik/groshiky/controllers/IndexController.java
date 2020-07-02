package com.zontik.groshiky.controllers;

import com.zontik.groshiky.model.User;
import com.zontik.groshiky.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class IndexController extends BaseController{

    private final IUserService userService;

    @Autowired
    public IndexController(IUserService userService) {
        this.userService = userService;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap model) {
        User user = new User();
        model.addAttribute("user", user);
        return "index";
    }

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(@RequestParam(value = "error", required = false)String error,
                        Model model) {
        User user = new User();
        model.addAttribute("user", user);
        model.addAttribute("error", error!=null);
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
        User userWithoutPassword = new User();
        userWithoutPassword.setLogin(user.getLogin());
        model.addAttribute("user", userWithoutPassword);
        model.addAttribute("registered", true);
        return "index";
    }

    @RequestMapping(value = "/dashboard", method = RequestMethod.GET)
    public String dashboard(Model model) {
        User user = userService.findUserById(getUserId());
        model.addAttribute("accounts", user.getAccounts());
        return "dashboard";
    }

//    @RequestMapping(value = "/about-user/{id}", method = RequestMethod.GET)
//    public String getUser(@RequestParam(name = "id") Integer id) {
//        User user = userService.findUserById(id);
//
//        return "about-user";
//    }
}
