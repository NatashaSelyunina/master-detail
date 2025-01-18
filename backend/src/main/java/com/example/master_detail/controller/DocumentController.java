package com.example.master_detail.controller;

import com.example.master_detail.dto.DocumentDto;
import com.example.master_detail.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/document")
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping
    public DocumentDto createDocument(@RequestBody DocumentDto documentDto) {
        return documentService.createDocument(documentDto);
    }

    @GetMapping("/all")
    public List<DocumentDto> getAllDocuments() {
        return documentService.getAll();
    }
}
