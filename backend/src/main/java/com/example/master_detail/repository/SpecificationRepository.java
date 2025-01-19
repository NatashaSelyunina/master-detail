package com.example.master_detail.repository;

import com.example.master_detail.entity.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface SpecificationRepository extends JpaRepository<Specification, Long> {

    @Query("SELECT CASE WHEN COUNT(s) > 0 THEN true ELSE false END " +
            "FROM Specification s " +
            "WHERE s.document.id = :documentId AND s.title = :title")
    boolean existsSpecificationTitleByDocumentId(@Param("documentId") Long documentId, @Param("title") String title);

    @Query("SELECT COALESCE(SUM(s.sum), 0) FROM Specification s WHERE s.document.id = :documentId")
    BigDecimal getTotalSumByDocumentId(@Param("documentId") Long documentId);
}
