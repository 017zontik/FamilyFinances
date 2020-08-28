package com.zontik.groshiky.model;

import lombok.Data;
import java.text.SimpleDateFormat;

@Data
public class TransactionDto {

    private Integer id;
    private String date;
    private String name;
    private Double amount;
    private TransactionType TransactionType;
    private Integer accountId;

    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm");
}
