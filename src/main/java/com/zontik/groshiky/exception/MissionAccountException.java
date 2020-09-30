package com.zontik.groshiky.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MissionAccountException extends RuntimeException {
    public MissionAccountException() {
    }

    public MissionAccountException(String message) {
        super(message);
    }
}
