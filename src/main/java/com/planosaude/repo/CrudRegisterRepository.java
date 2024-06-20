package com.planosaude.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.planosaude.entity.BeneficiarioEntity;

public interface CrudRegisterRepository extends JpaRepository<BeneficiarioEntity, Integer> {

}
