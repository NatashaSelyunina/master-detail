package com.example.master_detail.service.impl;

import com.example.master_detail.dto.SpecificationDto;
import com.example.master_detail.entity.Document;
import com.example.master_detail.entity.Specification;
import com.example.master_detail.exception_handling.IncorrectInformationException;
import com.example.master_detail.repository.SpecificationRepository;
import com.example.master_detail.service.ErrorService;
import com.example.master_detail.service.SpecificationService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class SpecificationServiceImpl implements SpecificationService {

    private final SpecificationRepository specificationRepository;

    private final ErrorService errorService;

    public SpecificationServiceImpl(SpecificationRepository specificationRepository, ErrorService errorService) {
        this.specificationRepository = specificationRepository;
        this.errorService = errorService;
    }

    @Override
    public List<Specification> save(List<SpecificationDto> specificationDtos, Document document) {
        if (checkDuplicateTitle(specificationDtos)) {
            errorService.save(
                    "Check the specifications, you are trying to add different specifications with the same name");
            throw new IncorrectInformationException(
                    "Check the specifications, you are trying to add different specifications with the same name", HttpStatus.BAD_REQUEST);
        }
        List<Specification> specifications = new ArrayList<>();

        for (SpecificationDto specificationDto : specificationDtos) {
            if (specificationDto.getTitle() == null || specificationDto.getTitle().isEmpty()) {
                throw new IncorrectInformationException("The specification must have a name", HttpStatus.BAD_REQUEST);
            }

            Specification savedSpecification = new Specification();
            savedSpecification.setTitle(specificationDto.getTitle().trim().toLowerCase());
            savedSpecification.setSum(specificationDto.getSum());
            savedSpecification.setDocument(document);
            specifications.add(savedSpecification);
        }
        return specificationRepository.saveAll(specifications);
    }

    @Override
    public void save(Specification specification) {
        specificationRepository.save(specification);
    }

    @Override
    public void isExistTitleByDocumentId(String title, Long documentId) {
        if (specificationRepository.existsSpecificationTitleByDocumentId(documentId, title)) {
            errorService.save("The document already has a specification with a name: " + title);
            throw new IncorrectInformationException("The document already has a specification with a name: " + title, HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public BigDecimal getTotalSumByDocumentId(Long documentId) {
        return specificationRepository.getTotalSumByDocumentId(documentId);
    }

    @Override
    public Specification getById(Long id) {
        return specificationRepository.findById(id).orElseThrow(
                () -> new IncorrectInformationException("Specification with id: " + id + " not found", HttpStatus.BAD_REQUEST));
    }

    @Override
    public void delete(Specification specification) {
        specificationRepository.delete(specification);
    }

    public boolean checkDuplicateTitle(List<SpecificationDto> specificationDtos) {
        return specificationDtos.stream()
                .map(SpecificationDto::getTitle)
                .distinct()
                .count() != specificationDtos.size();
    }
}
