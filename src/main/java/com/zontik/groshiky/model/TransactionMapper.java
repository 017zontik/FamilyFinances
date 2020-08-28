package com.zontik.groshiky.model;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Objects;

@Component
public class TransactionMapper {

    @Autowired
    private ModelMapper mapper;

    public Transaction toEntity(TransactionDto dto) {
        return Objects.isNull(dto) ? null : mapper.map(dto, Transaction.class);
    }

    public TransactionDto toDto(Transaction entity) {
        return Objects.isNull(entity) ? null : mapper.map(entity, TransactionDto.class);
    }
}
