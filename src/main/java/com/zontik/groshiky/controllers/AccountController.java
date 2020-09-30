package com.zontik.groshiky.controllers;

import com.zontik.groshiky.exception.MissionTransactionException;
import com.zontik.groshiky.model.*;
import com.zontik.groshiky.service.IAccountService;
import com.zontik.groshiky.service.ITransactionService;
import com.zontik.groshiky.service.IUserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import javax.persistence.EntityNotFoundException;
import java.util.*;

@RestController("/dashboard")
public class AccountController extends BaseController {

    private final IAccountService accountService;
    private final IUserService userService;
    private final ITransactionService transactionService;
    private final ModelMapper mapper;


    @Autowired
    public AccountController(IAccountService accountService, IUserService userService,
                             ITransactionService transactionService, ModelMapper mapper) {
        this.accountService = accountService;
        this.userService = userService;
        this.transactionService = transactionService;
        this.mapper = mapper;
    }


    @PostMapping(value = "/addAccount")
    public ResponseEntity addAccount(Account account) {
        account.setUser(userService.findUserById(getUserId()));
        if (accountService.getAccountByName(account.getName(), account.getUser().getId()) != null) {
            String message = String.format("The account \"%s\" already exists", account.getName());
            return new ResponseEntity(message, HttpStatus.BAD_REQUEST);
        }
        accountService.addAccount(account);
        AccountModel newAccount = new AccountModel(account);
        return new ResponseEntity(newAccount, HttpStatus.OK);
    }

    @GetMapping(value = "/transactions")
    public List<TransactionDto> getTransactions(Integer accountId) {
        List<Transaction> transactionList = (accountService.findAccountById(accountId)).getTransactions();
        List<TransactionDto> transactionDtos = new ArrayList<>();
        for (Transaction transaction : transactionList) {
            transactionDtos.add(mapper.map(transaction, TransactionDto.class));
        }
        return transactionDtos;
    }

    @PostMapping(value = "/addTransaction")
    public Transaction addTransaction(Transaction transaction, Integer accountId) {
        Account account = (accountService.findAccountById(accountId));
        transactionService.addTransaction(transaction, account);
        return transaction;
    }

    @GetMapping(value = "/account")
    public AccountModel getAccount(Integer id){
      AccountModel account = new AccountModel(accountService.findAccountById(id));
      return account;
    }

    @GetMapping(value = "/transactions/{id}")
    public TransactionDto getTransaction(@PathVariable Integer id){
        return mapper.map(transactionService.findTransactionById(id), TransactionDto.class);
    }

    @DeleteMapping(value = "/transactions/{id}")
    public void deleteTransaction(@PathVariable Integer id){
        transactionService.deleteTransactionById(id);
    }

    @PutMapping(value = "/transactions/{id}")
    public TransactionDto updateTransaction(@PathVariable Integer id, TransactionDto transactionDto) {
        Transaction tr = mapper.map(transactionDto, Transaction.class);
        return mapper.map(transactionService.editTransaction(tr), TransactionDto.class);
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({EntityNotFoundException.class, MissionTransactionException.class})
    protected String handleConflict(RuntimeException ex) {
        return ex.getMessage();
    }

}

