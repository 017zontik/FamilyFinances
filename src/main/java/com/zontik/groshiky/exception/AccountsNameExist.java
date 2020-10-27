package com.zontik.groshiky.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class AccountsNameExist extends RuntimeException {

    public AccountsNameExist () {
        super();
    }

    public AccountsNameExist (String message) {
        super(message);
    }


}
