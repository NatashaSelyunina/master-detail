package com.example.master_detail.service;

import com.example.master_detail.dto.DocumentDto;
import com.example.master_detail.dto.SpecificationDto;

public interface DocumentSpecificationService {

    DocumentDto createDocument(DocumentDto documentDto);

    SpecificationDto addSpecification(SpecificationDto specificationDto, Long documentId);

    void deleteSpecification(Long id);

    SpecificationDto updateSpecification(SpecificationDto specificationDto);
}
