package com.example.master_detail.service;

import com.example.master_detail.dto.SpecificationDto;
import com.example.master_detail.entity.Document;
import com.example.master_detail.entity.Specification;

import java.util.List;

public interface SpecificationService {

    List<Specification> save(List<SpecificationDto> specificationDtos, Document document);

    SpecificationDto add(SpecificationDto specificationDto, Long documentId);
}
