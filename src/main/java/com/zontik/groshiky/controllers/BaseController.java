package com.zontik.groshiky.controllers;

import com.zontik.groshiky.model.User;
import org.springframework.security.core.context.SecurityContextHolder;


public abstract class BaseController {
    protected Integer getUserId() {
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return principal.getId();
    }
}
