package com.zontik.groshiky.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class MissionTransactionException extends RuntimeException{
    public MissionTransactionException() {
    }

    public MissionTransactionException(String message) {
        super(message);
    }

    public MissionTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public MissionTransactionException(Throwable cause) {
        super(cause);
    }


}
