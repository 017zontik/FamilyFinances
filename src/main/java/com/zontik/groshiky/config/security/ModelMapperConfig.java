package com.zontik.groshiky.config.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.zontik.groshiky.model.Account;
import com.zontik.groshiky.model.Transaction;
import com.zontik.groshiky.model.TransactionDto;
import com.zontik.groshiky.repository.AccountRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.PostConstruct;

import java.util.TimeZone;

import static org.modelmapper.config.Configuration.AccessLevel.PRIVATE;

@Configuration
public class ModelMapperConfig {

    final public AccountRepository accountRepository;

    @Autowired
    public ModelMapperConfig(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    @PostConstruct
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper mapper = new ModelMapper();
        Converter<Integer, Account> transactionIdToTransaction = context -> accountRepository.findAccountById(context.getSource());
        Converter<Account, Integer> accountToAccoutId = context -> context.getSource().getId();
        mapper.getConfiguration()
                .setFieldMatchingEnabled(true)
                .setSkipNullEnabled(true)
                .setFieldAccessLevel(PRIVATE);
        mapper.createTypeMap(TransactionDto.class, Transaction.class)
                .addMappings(mapping -> mapping.using(transactionIdToTransaction).map(TransactionDto::getAccountId, Transaction::setAccount));
        mapper.createTypeMap(Transaction.class, TransactionDto.class)
                .addMappings(mapping -> mapping.using(accountToAccoutId).map(Transaction::getAccount, TransactionDto::setAccountId));
        return mapper;
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setTimeZone(TimeZone.getDefault());
        return objectMapper;
    }

}
