package com.zontik.groshiky.service;

import com.zontik.groshiky.exception.AccountsNameExist;
import com.zontik.groshiky.exception.NotFoundAccountException;
import com.zontik.groshiky.exception.NotFoundTransactionException;
import com.zontik.groshiky.repository.AccountRepository;
import com.zontik.groshiky.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class AccountService implements IAccountService{

    private final AccountRepository accountRepository;
    private final static double DEFAULT_BALANCE = 0d;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @Override
    public Account addAccount(Account account) {
        account.setBalance(DEFAULT_BALANCE);
        return accountRepository.save(account);
    }

    @Override
    public Account getAccountByName(String name, Integer id) {
        return accountRepository.findByNameAndUserId(name, id);
    }

    @Override
    public Account findAccountById(Integer id) {
        return accountRepository.findById(id)
                .orElseThrow(() -> new NotFoundTransactionException("Unable to find account with id " + id));
    }

    @Override
    public Account editAccount(Account account) {
        Account ac = accountRepository.findById(account.getId())
                .orElseThrow(() -> new NotFoundAccountException("Unable to find account with id " + account.getId()));;

        if (getAccountByName(account.getName(), ac.getUser().getId()) != null) {
            String message = String.format("The account \"%s\" already exists", account.getName());
            throw  new AccountsNameExist(message);
        } else {
            ac.setName(account.getName());
            return accountRepository.save(ac);
        }

    }
}
