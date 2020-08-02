package com.zontik.groshiky.model;

import lombok.Data;

@Data
public class AccountModel {
    private String name;
    private Integer id;
    private Double balance;

    public AccountModel(Account account) {
        this.id = account.getId();
        this.name = account.getName();
        this.balance = account.getBalance();
    }
}
