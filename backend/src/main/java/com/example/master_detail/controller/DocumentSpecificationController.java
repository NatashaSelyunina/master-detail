package com.example.master_detail.controller;

import com.example.master_detail.dto.DocumentDto;
import com.example.master_detail.dto.SpecificationDto;
import com.example.master_detail.service.DocumentSpecificationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/docs")
@CrossOrigin(origins = "http://localhost:3000")
public class DocumentSpecificationController {

    private final DocumentSpecificationService documentSpecificationService;

    public DocumentSpecificationController(DocumentSpecificationService documentSpecificationService) {
        this.documentSpecificationService = documentSpecificationService;
    }

    @PostMapping("/document")
    public DocumentDto createDocument(@Valid @RequestBody DocumentDto documentDto) {
        return documentSpecificationService.createDocument(documentDto);
    }

    @PostMapping("/specification/{document-id}")
    SpecificationDto addSpecification(@Valid @RequestBody SpecificationDto specificationDto, @PathVariable("document-id") Long documentId) {
        return documentSpecificationService.addSpecification(specificationDto, documentId);
    }

    @PutMapping("/specification")
    SpecificationDto updateSpecification(@Valid @RequestBody SpecificationDto specificationDto) {
        return documentSpecificationService.updateSpecification(specificationDto);
    }

    @DeleteMapping("/specification/{id}")
    void deleteSpecification(@PathVariable("id") Long specificationId) {
        documentSpecificationService.deleteSpecification(specificationId);
    }
}
