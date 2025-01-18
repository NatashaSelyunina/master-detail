package com.example.master_detail.service;

import com.example.master_detail.dto.SpecificationDto;
import com.example.master_detail.entity.Specification;

import java.util.Set;

public interface SpecificationService {

    Set<Specification> save(Set<SpecificationDto> specificationDtos);

    SpecificationDto add(SpecificationDto specificationDto, Long documentId);
}
