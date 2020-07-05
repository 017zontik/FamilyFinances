package com.zontik.groshiky.model;

import lombok.Data;

@Data
public class AccountResponse {
    private Result result;
    private String message;
    private Account account;

    public enum Result {
        OK,
        ERROR
    }

    public AccountResponse(String message) {
        this.result = Result.ERROR;
        this.message = message;
    }

    public AccountResponse(Account account) {
        this.result = Result.OK;
        this.account = account;
    }
}
