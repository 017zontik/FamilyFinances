package com.zontik.groshiky.service;

import com.zontik.groshiky.model.Account;
import com.zontik.groshiky.model.Transaction;
import com.zontik.groshiky.model.TransactionDto;

public interface ITransactionService {
    Transaction addTransaction(Transaction transaction, Account account);
    void deleteTransactionById(Integer id);
    Transaction findTransactionById(Integer id);
    Transaction editTransaction(TransactionDto transactionDto);
}
