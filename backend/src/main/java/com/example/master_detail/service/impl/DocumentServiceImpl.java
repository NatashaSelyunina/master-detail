package com.example.master_detail.service.impl;

import com.example.master_detail.dto.DocumentDto;
import com.example.master_detail.entity.Document;
import com.example.master_detail.repository.DocumentRepository;
import com.example.master_detail.service.DocumentService;
import lombok.AllArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Setter
@Service
public class DocumentServiceImpl implements DocumentService {

    private final DocumentRepository documentRepository;

    @Override
    public DocumentDto createDocument(DocumentDto documentDto) {

        Document document = new Document();
        document.setId(0L);
        document.setNumber(documentDto.getNumber().trim());
        document.setDate(documentDto.getDate());
        document.setSum(documentDto.getSum());
        document.setNote(documentDto.getNote().trim());
        document.setSpecifications();
        documentRepository.save(document);
        return DocumentDto.from(document);
    }
}
