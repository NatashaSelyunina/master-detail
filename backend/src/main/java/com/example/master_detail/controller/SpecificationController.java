package com.example.master_detail.controller;

import com.example.master_detail.dto.SpecificationDto;
import com.example.master_detail.service.SpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/specification")
public class SpecificationController {

    private final SpecificationService specificationService;

    @PostMapping("/{document-id}")
    SpecificationDto save(@RequestBody SpecificationDto specificationDto, Long documentId) {
        return specificationService.save(specificationDto, documentId);
    }
}
