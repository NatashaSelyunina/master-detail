package com.example.master_detail.controller;

import com.example.master_detail.dto.DocumentDto;
import com.example.master_detail.service.DocumentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/document")
public class DocumentController {

    private final DocumentService documentService;

    @PostMapping
    public DocumentDto createDocument(@RequestBody DocumentDto documentDto) {
        return documentService.createDocument(documentDto);
    }
}
