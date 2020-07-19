package com.zontik.groshiky.model;

import lombok.Data;
import java.time.format.DateTimeFormatter;

@Data
public class TransactionModel {
    private Integer id;
    private String date;
    private String name;
    private Float amount;

    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public TransactionModel(Transaction transaction) {
        this.id = transaction.getId();
        this.date = (transaction.getDate()).format(formatter);
        this.name = transaction.getName();
        this.amount = transaction.getAmount();
    }
}
