package com.example.master_detail.service.impl;

import com.example.master_detail.dto.DocumentDto;
import com.example.master_detail.dto.SpecificationDto;
import com.example.master_detail.entity.Document;
import com.example.master_detail.entity.Specification;
import com.example.master_detail.service.DocumentService;
import com.example.master_detail.service.DocumentSpecificationService;
import com.example.master_detail.service.SpecificationService;
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
    public DocumentDto createDocument(DocumentDto documentDto) {
        String number = documentDto.getNumber().trim().toLowerCase();
        documentService.isExistsByNumber(number);

        Document document = new Document();
        document.setNumber(documentDto.getNumber().trim().toLowerCase());
        document.setDate(documentDto.getDate());
        document.setNote(documentDto.getNote().toLowerCase());
        documentService.save(document);

        List<Specification> specifications = specificationService.save(documentDto.getSpecifications(), document);
        BigDecimal documentSum = specifications.stream()
                .map(Specification::getSum)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        document.setSum(documentSum);

        documentService.save(document);
        return DocumentDto.from(document);
    }

    @Override
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
    public SpecificationDto updateSpecification(SpecificationDto specificationDto) {
        Specification specification = specificationService.getById(specificationDto.getId());
        Document document = specification.getDocument();
        documentService.getById(document.getId());

        specification.setTitle(specificationDto.getTitle().trim().toLowerCase());
        specification.setSum(specificationDto.getSum());
        specificationService.save(specification);

        BigDecimal documentSum = specificationService.getTotalSumByDocumentId(document.getId());
        document.setSum(documentSum);
        documentService.save(document);

        return SpecificationDto.from(specification);
    }
}
