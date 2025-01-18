package com.example.master_detail.service.impl;

import com.example.master_detail.dto.DocumentDto;
import com.example.master_detail.entity.Document;
import com.example.master_detail.entity.Specification;
import com.example.master_detail.repository.DocumentRepository;
import com.example.master_detail.service.DocumentService;
import com.example.master_detail.service.SpecificationService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@Setter
@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;
    private final SpecificationService specificationService;

    @Override
    public DocumentDto createDocument(DocumentDto documentDto) {
        String number = documentDto.getNumber().trim().toLowerCase();
        isExistsByNumber(number);

        Document document = new Document();
        document.setId(0L);
        document.setNumber(documentDto.getNumber().trim().toLowerCase());
        document.setDate(documentDto.getDate());
        document.setNote(document.getNote().toLowerCase());

        List<Specification> specifications = specificationService.save(documentDto.getSpecifications(), document);
        BigDecimal documentSum = specifications.stream()
                        .map(Specification::getSum)
                        .reduce(BigDecimal.ZERO, BigDecimal::add);
        document.setSum(documentSum);
        documentRepository.save(document);
        return DocumentDto.from(document);
    }

    @Override
    public List<DocumentDto> getAll() {
        return DocumentDto.from(documentRepository.findAll());
    }

    @Override
    public Document getById(Long id) {
        return documentRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Document with id: " + id + " not found"));
    }

    public void isExistsByNumber(String number) {
        if (documentRepository.existByNumber(number)) {
            throw new RuntimeException("Document with number: " + number + " already exist");
        }
    }

    @Override
    public void save(Document document) {
        documentRepository.save(document);
    }
}
