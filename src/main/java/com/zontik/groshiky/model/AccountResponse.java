package com.zontik.groshiky.model;

import lombok.Data;

@Data
public class AccountResponse {
    private Result result;
    private String message;
    private AccountModel accountModel;

    public enum Result {
        OK,
        ERROR
    }

    public AccountResponse(String message) {
        this.result = Result.ERROR;
        this.message = message;
    }

    public AccountResponse(AccountModel accountModel) {
        this.result = Result.OK;
        this.accountModel = accountModel;
    }
}
