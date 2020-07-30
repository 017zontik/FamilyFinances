package com.zontik.groshiky.service;

import com.zontik.groshiky.model.Account;
import com.zontik.groshiky.model.Transaction;

public interface ITransactionService {
    Transaction addTransaction(Transaction transaction, Account account);
    void deleteById(Integer id);

}
