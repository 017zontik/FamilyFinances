package com.zontik.groshiky.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundTransactionException extends RuntimeException{
    public NotFoundTransactionException() {
    }

    public NotFoundTransactionException(String message) {
        super(message);
    }

    public NotFoundTransactionException(String message, Throwable cause) {
        super(message, cause);
    }

    public NotFoundTransactionException(Throwable cause) {
        super(cause);
    }


}
