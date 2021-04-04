package com.example.ExomeAnalysisPlatform.repository;

import com.example.ExomeAnalysisPlatform.entity.ExomeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ExomeRepository extends JpaRepository<ExomeEntity, Long> {
    Page<ExomeEntity> findAllByNameContainingIgnoreCase(String name, Pageable pageable);
}
