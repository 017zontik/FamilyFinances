package com.zontik.groshiky.service;

import com.zontik.groshiky.exception.MissionTransactionException;
import com.zontik.groshiky.model.*;
import com.zontik.groshiky.repository.AccountRepository;
import com.zontik.groshiky.repository.TransactionRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;


@RequiredArgsConstructor
@Service
@Transactional
public class TransactionService implements ITransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountRepository accountRepository;
    private final ModelMapper modelMapper;


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
    public Transaction editTransaction(TransactionDto transactionDto) {
        Account account = (accountRepository.findAccountById(transactionDto.getAccountId()));
        Transaction tr = transactionRepository.findTransactionById(transactionDto.getId());
        Double newBalance = (account.getBalance()) - tr.getAmount();
        if (transactionDto.getTransactionType().equals(TransactionType.INCOME)) {
            account.setBalance(newBalance + transactionDto.getAmount());
        } else {
            transactionDto.setAmount(transactionDto.getAmount() * (-1));
            account.setBalance(newBalance + transactionDto.getAmount());
        }
        modelMapper.map(transactionDto, tr);
        return transactionRepository.save(tr);
    }
}
