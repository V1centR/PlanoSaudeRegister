package com.planosaude.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planosaude.entity.DocumentoEntity;

public interface DocumentoRepository extends JpaRepository<DocumentoEntity, Integer> {

}
