package com.example.master_detail.service.impl;

import com.example.master_detail.entity.Error;
import com.example.master_detail.repository.ErrorRepository;
import com.example.master_detail.service.ErrorService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ErrorServiceImpl implements ErrorService {

    public final ErrorRepository errorRepository;

    public ErrorServiceImpl(ErrorRepository errorRepository) {
        this.errorRepository = errorRepository;
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void save(String errorMessage) {
        Error newError = new Error();
        newError.setDate(LocalDate.now());
        newError.setMessage(errorMessage);
        errorRepository.save(newError);
    }
}
