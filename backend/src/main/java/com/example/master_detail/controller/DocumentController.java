package com.example.master_detail.controller;

import com.example.master_detail.dto.DocumentDto;
import com.example.master_detail.service.DocumentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/all")
    public List<DocumentDto> getAllDocuments() {
        return documentService.getAll();
    }
}
