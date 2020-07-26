package com.zontik.groshiky.service;

import com.zontik.groshiky.model.Account;
import com.zontik.groshiky.model.Transaction;
import com.zontik.groshiky.model.TypeTransactions;

public interface ITransactionService {
    Transaction addTransaction(Transaction transaction, Account account);

}
