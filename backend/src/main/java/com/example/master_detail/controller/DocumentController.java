package com.example.master_detail.controller;

import com.example.master_detail.dto.DocumentDto;
import com.example.master_detail.service.DocumentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/document")
@CrossOrigin(origins = "http://localhost:3000")
public class DocumentController {

    private final DocumentService documentService;

    public DocumentController(DocumentService documentService) {
        this.documentService = documentService;
    }

    @GetMapping("/all")
    public List<DocumentDto> getAllDocuments() {
        return documentService.getAll();
    }

    @PutMapping
    public DocumentDto updateDocument(@Valid @RequestBody DocumentDto documentDto) {
        return documentService.updateDocument(documentDto);
    }

    @DeleteMapping("/{id}")
    public void deleteDocument(@PathVariable("id") Long id) {
        documentService.delete(id);
    }
}
