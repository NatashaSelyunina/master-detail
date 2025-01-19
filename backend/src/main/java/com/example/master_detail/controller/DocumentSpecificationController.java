package com.example.master_detail.controller;

import com.example.master_detail.dto.DocumentDto;
import com.example.master_detail.dto.SpecificationDto;
import com.example.master_detail.service.DocumentSpecificationService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/specification")
public class DocumentSpecificationController {

    private final DocumentSpecificationService documentSpecificationService;

    public DocumentSpecificationController(DocumentSpecificationService documentSpecificationService) {
        this.documentSpecificationService = documentSpecificationService;
    }

    @PostMapping
    public DocumentDto createDocument(@RequestBody DocumentDto documentDto) {
        return documentSpecificationService.createDocument(documentDto);
    }

    @PostMapping("/{document-id}")
    SpecificationDto add(@RequestBody SpecificationDto specificationDto, @PathVariable("document-id") Long documentId) {
        return documentSpecificationService.addSpecification(specificationDto, documentId);
    }
}
