package com.zontik.groshiky.model;

import lombok.Data;
import java.text.SimpleDateFormat;

@Data
public class TransactionDto {
    private Integer id;
    private String date;
    private String name;
    private Float amount;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");

    public TransactionDto(Transaction transaction) {
        this.id = transaction.getId();
        this.date = formatter.format(transaction.getDate());
        this.name = transaction.getName();
        this.amount = transaction.getAmount();
    }
}
