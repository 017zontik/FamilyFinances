package com.zontik.groshiky.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundAccountException extends RuntimeException {
    public NotFoundAccountException() {
    }

    public NotFoundAccountException(String message) {
        super(message);
    }
}
