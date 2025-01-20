package com.example.master_detail.service;

import com.example.master_detail.dto.DocumentDto;
import com.example.master_detail.entity.Document;

import java.util.List;

public interface DocumentService {

    Document getById(Long id);

    List<DocumentDto> getAll();

    void save(Document document);

    void isExistsByNumber(String number);

    void delete(Long id);

    DocumentDto updateDocument(DocumentDto documentDto);
}
