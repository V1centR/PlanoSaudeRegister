package com.planosaude.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planosaude.entity.DocumentoEntity;

public interface DocumentoRepository extends JpaRepository<DocumentoEntity, Integer> {
	
	List<DocumentoEntity> findByBeneficiarioId(int idBeneficiario);

}
