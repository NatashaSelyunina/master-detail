package com.example.master_detail.repository;

import com.example.master_detail.entity.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;

public interface SpecificationRepository extends JpaRepository<Specification, Long> {

    boolean existsSpecificationTitleByDocumentId(Long documentId, String title);

    @Query("SELECT COALESCE(SUM(s.sum), 0) FROM Specification s WHERE s.document.id = :documentId")
    BigDecimal getTotalSumByDocumentId(@Param("documentId") Long documentId);
}
