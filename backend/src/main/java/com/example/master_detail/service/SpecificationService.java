package com.example.master_detail.service;

import com.example.master_detail.dto.SpecificationDto;
import com.example.master_detail.entity.Document;
import com.example.master_detail.entity.Specification;

import java.math.BigDecimal;
import java.util.List;

public interface SpecificationService {

    List<Specification> save(List<SpecificationDto> specificationDtos, Document document);

    void save(Specification specification);

    void isExistTitleByDocumentId(String title, Long documentId);

    BigDecimal getTotalSumByDocumentId(Long documentId);

    Specification getById(Long id);

    void delete(Specification specification);
}
