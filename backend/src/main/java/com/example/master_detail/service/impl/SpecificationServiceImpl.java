package com.example.master_detail.service.impl;

import com.example.master_detail.dto.SpecificationDto;
import com.example.master_detail.entity.Document;
import com.example.master_detail.entity.Specification;
import com.example.master_detail.repository.DocumentRepository;
import com.example.master_detail.repository.SpecificationRepository;
import com.example.master_detail.service.DocumentService;
import com.example.master_detail.service.SpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;

@RequiredArgsConstructor
@Service
public class SpecificationServiceImpl implements SpecificationService {

    private final SpecificationRepository specificationRepository;
    private final DocumentService documentService;

    @Override
    public Set<Specification> save(Set<SpecificationDto> specificationDtos) {
        Set<Specification> specifications = SpecificationDto.to(specificationDtos);

        for (Specification specification : specifications) {
            Specification savedSpecification =
                    new Specification(specification.getTitle().trim().toLowerCase(), specification.getSum());
            specificationRepository.save(savedSpecification);
        }
        return specifications;
    }

    @Override
    public SpecificationDto add(SpecificationDto specificationDto, Long documentId) {
        Document document = documentService.getById(documentId);

        String title = specificationDto.getTitle().trim().toLowerCase();
        isExistTitleByDocumentId();

        Specification specification =
                new Specification(title, specificationDto.getSum());

        return null;
    }

    private void isExistTitleByDocumentId(String title) {

    }
}
