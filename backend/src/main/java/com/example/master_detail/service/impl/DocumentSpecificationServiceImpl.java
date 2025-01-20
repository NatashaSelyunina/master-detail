package com.example.master_detail.service.impl;

import com.example.master_detail.dto.DocumentDto;
import com.example.master_detail.dto.SpecificationDto;
import com.example.master_detail.entity.Document;
import com.example.master_detail.entity.Specification;
import com.example.master_detail.service.DocumentService;
import com.example.master_detail.service.DocumentSpecificationService;
import com.example.master_detail.service.SpecificationService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class DocumentSpecificationServiceImpl implements DocumentSpecificationService {

    private final DocumentService documentService;

    private final SpecificationService specificationService;

    public DocumentSpecificationServiceImpl(DocumentService documentService, SpecificationService specificationService) {
        this.documentService = documentService;
        this.specificationService = specificationService;
    }

    @Override
    @Transactional
    public DocumentDto createDocument(DocumentDto documentDto) {
        String number = documentDto.getNumber().trim().toLowerCase();
        documentService.isExistsByNumber(number);

        Document document = new Document();
        document.setNumber(number);
        document.setDate(documentDto.getDate());
        if (documentDto.getNote() != null) {
            document.setNote(documentDto.getNote().trim().toLowerCase());
        }
        documentService.save(document);

        if (documentDto.getSpecifications() != null) {
            List<Specification> specifications = specificationService.save(documentDto.getSpecifications(), document);
            BigDecimal documentSum = specifications.stream()
                    .map(Specification::getSum)
                    .reduce(BigDecimal.ZERO, BigDecimal::add);
            document.setSpecifications(specifications);
            document.setSum(documentSum);
        } else {
            document.setSum(BigDecimal.ZERO);
        }
        documentService.save(document);

        return DocumentDto.from(document);
    }

    @Override
    @Transactional
    public SpecificationDto addSpecification(SpecificationDto specificationDto, Long documentId) {
        Document document = documentService.getById(documentId);

        String title = specificationDto.getTitle().trim().toLowerCase();
        specificationService.isExistTitleByDocumentId(title, documentId);

        Specification specification = new Specification();
        specification.setSum(specificationDto.getSum());
        specification.setTitle(title);
        specification.setDocument(document);
        specificationService.save(specification);

        BigDecimal documentSum = specificationService.getTotalSumByDocumentId(documentId);
        document.setSum(documentSum);
        documentService.save(document);

        return SpecificationDto.from(specification);
    }

    @Override
    @Transactional
    public void deleteSpecification(Long id) {
        Specification specification = specificationService.getById(id);
        Document document = specification.getDocument();
        documentService.getById(document.getId());
        specificationService.delete(specification);

        BigDecimal documentSum = specificationService.getTotalSumByDocumentId(document.getId());
        document.setSum(documentSum);
        documentService.save(document);
    }

    @Override
    @Transactional
    public SpecificationDto updateSpecification(SpecificationDto specificationDto) {
        Specification specification = specificationService.getById(specificationDto.getId());
        Document document = specification.getDocument();
        documentService.getById(document.getId());

        String title = specificationDto.getTitle().trim().toLowerCase();
        if (!title.equals(specification.getTitle())) {
            specificationService.isExistTitleByDocumentId(title, document.getId());
            specification.setTitle(title);
        }

        specification.setSum(specificationDto.getSum());
        specificationService.save(specification);

        BigDecimal documentSum = specificationService.getTotalSumByDocumentId(document.getId());
        document.setSum(documentSum);
        documentService.save(document);

        return SpecificationDto.from(specification);
    }
}
