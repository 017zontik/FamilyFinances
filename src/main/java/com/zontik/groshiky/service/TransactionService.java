package com.zontik.groshiky.service;

import com.zontik.groshiky.model.Account;
import com.zontik.groshiky.model.Transaction;
import com.zontik.groshiky.model.TypeTransactions;
import com.zontik.groshiky.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Override
    public Transaction addTransaction(Transaction transaction, Account account) {
        if(transaction.getType_transactions() == TypeTransactions.INCOME){
            account.setBalance(account.getBalance() + transaction.getAmount());
        }else{
            transaction.setAmount(transaction.getAmount()*(-1));
            account.setBalance(account.getBalance() + transaction.getAmount());
        }
        transaction.setAccount(account);
        return transactionRepository.save(transaction);
    }




    }
