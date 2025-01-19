package com.example.master_detail.repository;

import com.example.master_detail.entity.Error;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ErrorRepository extends JpaRepository<Error, Long> {

}
