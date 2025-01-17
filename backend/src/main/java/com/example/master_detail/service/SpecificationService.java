package com.example.master_detail.service;

import com.example.master_detail.dto.SpecificationDto;

public interface SpecificationService {

    SpecificationDto save(SpecificationDto specificationDto, Long documentId);
}
