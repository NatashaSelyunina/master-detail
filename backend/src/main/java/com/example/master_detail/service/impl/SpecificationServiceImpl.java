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

import java.math.BigDecimal;
import java.util.List;

@RequiredArgsConstructor
@Service
public class SpecificationServiceImpl implements SpecificationService {

    private final SpecificationRepository specificationRepository;
    private final DocumentService documentService;

    @Override
    public List<Specification> save(List<SpecificationDto> specificationDtos, Document document) {
        List<Specification> specifications = SpecificationDto.to(specificationDtos);

        for (Specification specification : specifications) {
            Specification savedSpecification =
                    new Specification(specification.getTitle().trim().toLowerCase(), specification.getSum());
            savedSpecification.setDocument(document);
            specificationRepository.save(savedSpecification);
        }
        return specifications;
    }

    @Override
    public SpecificationDto add(SpecificationDto specificationDto, Long documentId) {
        Document document = documentService.getById(documentId);

        String title = specificationDto.getTitle().trim().toLowerCase();
        isExistTitleByDocumentId(title, documentId);

        Specification specification = new Specification(title, specificationDto.getSum());
        specification.setDocument(document);
        specificationRepository.save(specification);

        BigDecimal documentSum = specificationRepository.getTotalSumByDocumentId(documentId);
        document.setSum(documentSum.add(specification.getSum()));
        documentService.save(document);

        return SpecificationDto.from(specification);
    }

    private void isExistTitleByDocumentId(String title, Long documentId) {
        if (specificationRepository.existsSpecificationTitleByDocumentId(documentId, title)) {
            throw new RuntimeException("The document already has a specification with a name: " + title);
        }
    }
}
