package com.example.master_detail.repository;

import com.example.master_detail.entity.Specification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SpecificationRepository extends JpaRepository<Specification, Long> {

    boolean existsSpecificationTitleByDocumentId(Long documentId, String title);
}
