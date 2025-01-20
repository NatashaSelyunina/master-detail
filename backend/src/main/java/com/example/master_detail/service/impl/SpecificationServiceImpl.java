package com.example.master_detail.service.impl;

import com.example.master_detail.dto.SpecificationDto;
import com.example.master_detail.entity.Document;
import com.example.master_detail.entity.Specification;
import com.example.master_detail.repository.SpecificationRepository;
import com.example.master_detail.service.SpecificationService;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    private final SpecificationRepository specificationRepository;

    public SpecificationServiceImpl(SpecificationRepository specificationRepository) {
        this.specificationRepository = specificationRepository;
    }

    @Override
    public List<Specification> save(List<SpecificationDto> specificationDtos, Document document) {
        List<Specification> specifications = SpecificationDto.to(specificationDtos);

        for (Specification specification : specifications) {
            Specification savedSpecification = new Specification();
            savedSpecification.setTitle(specification.getTitle().trim().toLowerCase());
            savedSpecification.setSum(specification.getSum());
            savedSpecification.setDocument(document);
            specificationRepository.save(savedSpecification);
        }
        return specifications;
    }

    @Override
    public void save(Specification specification) {
        specificationRepository.save(specification);
    }

    @Override
    public void isExistTitleByDocumentId(String title, Long documentId) {
        if (specificationRepository.existsSpecificationTitleByDocumentId(documentId, title)) {
            throw new RuntimeException("The document already has a specification with a name: " + title);
        }
    }

    @Override
    public BigDecimal getTotalSumByDocumentId(Long documentId) {
        return specificationRepository.getTotalSumByDocumentId(documentId);
    }

    @Override
    public Specification getById(Long id) {
        return specificationRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Specification with id: " + id + " not found"));
    }

    @Override
    public void delete(Specification specification) {
        specificationRepository.delete(specification);
    }
}
