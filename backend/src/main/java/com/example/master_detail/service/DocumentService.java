package com.example.master_detail.service;

import com.example.master_detail.dto.DocumentDto;
import com.example.master_detail.entity.Document;

public interface DocumentService {

    DocumentDto createDocument(DocumentDto documentDto);

    Document getById(Long id);
}
