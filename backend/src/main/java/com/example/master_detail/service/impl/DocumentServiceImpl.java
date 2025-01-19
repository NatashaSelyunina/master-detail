package com.example.master_detail.service.impl;

import com.example.master_detail.dto.DocumentDto;
import com.example.master_detail.entity.Document;
import com.example.master_detail.repository.DocumentRepository;
import com.example.master_detail.service.DocumentService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    public DocumentServiceImpl(DocumentRepository documentRepository) {
        this.documentRepository = documentRepository;
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

    @Override
    public void isExistsByNumber(String number) {
        if (documentRepository.existsByNumber(number)) {
            throw new RuntimeException("Document with number: " + number + " already exist");
        }
    }

    @Override
    public void save(Document document) {
        documentRepository.save(document);
    }
}
