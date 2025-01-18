package com.example.master_detail.controller;

import com.example.master_detail.dto.SpecificationDto;
import com.example.master_detail.service.SpecificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/specification")
public class SpecificationController {

    private final SpecificationService specificationService;

    @PostMapping("/{document-id}")
    SpecificationDto add(@RequestBody SpecificationDto specificationDto, @PathVariable("document-id") Long documentId) {
        return specificationService.add(specificationDto, documentId);
    }
}
