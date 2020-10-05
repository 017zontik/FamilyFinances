package com.zontik.groshiky.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zontik.groshiky.format.DateFormat;
import lombok.Data;
import java.util.Date;

@Data
public class TransactionDto {

    private Integer id;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = DateFormat.DATEFORMAT)
    private Date date;
    private String name;
    private Double amount;
    private TransactionType transactionType;
    private Integer accountId;


}
