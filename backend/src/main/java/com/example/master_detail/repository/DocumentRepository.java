package com.example.master_detail.repository;

import com.example.master_detail.entity.Document;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends JpaRepository<Document, Long> {

    boolean existsByNumber(String number);
}
