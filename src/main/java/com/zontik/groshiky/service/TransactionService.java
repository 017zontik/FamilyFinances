package com.zontik.groshiky.service;

import com.zontik.groshiky.exception.MissionTransactionException;
import com.zontik.groshiky.model.*;
import com.zontik.groshiky.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.EntityNotFoundException;


@RequiredArgsConstructor
@Service
@Transactional
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;

    @Override
    public Transaction addTransaction(Transaction transaction, Account account) {
        if (transaction.getTransactionType().equals(TransactionType.INCOME)) {
            account.setBalance(account.getBalance() + transaction.getAmount());
        } else {
            transaction.setAmount(transaction.getAmount() * (-1));
            account.setBalance(account.getBalance() + transaction.getAmount());
        }
        transaction.setAccount(account);
        return transactionRepository.save(transaction);
    }

    @Override
    public void deleteTransactionById(Integer id) {
        Transaction transaction = transactionRepository.findById(id)
                .orElseThrow(() -> new MissionTransactionException("Unable to find transaction with id " + id));
        Account account = transaction.getAccount();
        if (transaction.getTransactionType() == TransactionType.INCOME) {
            account.setBalance(account.getBalance() - transaction.getAmount());
        } else {
            account.setBalance(account.getBalance() - transaction.getAmount());
        }
        transactionRepository.deleteById(id);
    }

    @Override
    public Transaction findTransactionById(Integer id) {
        return transactionRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Unable to find transaction with id " + id));
    }

    @Override
    public Transaction editTransaction(Transaction transaction) {
        Account account = transaction.getAccount();
        Transaction tr = transactionRepository.findTransactionById(transaction.getId());
        Double newBalance = (account.getBalance()) - tr.getAmount();
        if (transaction.getTransactionType().equals(TransactionType.INCOME)) {
            account.setBalance(newBalance + transaction.getAmount());
        } else {
            transaction.setAmount(transaction.getAmount() * (-1));
            account.setBalance(newBalance + transaction.getAmount());
        }
        return transactionRepository.save(transaction);
    }
}
