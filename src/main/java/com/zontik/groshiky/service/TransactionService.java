package com.zontik.groshiky.service;

import com.zontik.groshiky.model.Account;
import com.zontik.groshiky.model.Transaction;
import com.zontik.groshiky.model.TransactionType;
import com.zontik.groshiky.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }


    @Override
    public Transaction addTransaction(Transaction transaction, Account account) {
        if(transaction.getTransactionType() == TransactionType.INCOME){
            account.setBalance(account.getBalance() + transaction.getAmount());
        }else{
            transaction.setAmount(transaction.getAmount()*(-1));
            account.setBalance(account.getBalance() + transaction.getAmount());
        }
        transaction.setAccount(account);
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransactionById(Integer id) {
        Transaction transaction= transactionRepository.getOne(id);
        Account account = transaction.getAccount();
        if(transaction.getTransactionType()==TransactionType.INCOME){
            account.setBalance(account.getBalance()-transaction.getAmount());
        }else{
            account.setBalance(account.getBalance()-transaction.getAmount());
        }
        transactionRepository.deleteById(id);
    }

    @Override
    public Transaction findTransactionById(Integer id) {
        return transactionRepository.getOne(id);
    }


}
