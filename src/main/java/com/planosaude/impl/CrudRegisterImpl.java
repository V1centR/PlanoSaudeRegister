package com.planosaude.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.planosaude.entity.BeneficiarioEntity;
import com.planosaude.entity.DocumentoEntity;
import com.planosaude.repo.CrudRegisterRepository;
import com.planosaude.repo.DocumentoRepository;

import jakarta.transaction.Transactional;

@Service("crudRegisterImpl")
public class CrudRegisterImpl {
	
	@Autowired
	CrudRegisterRepository repo;
	
	@Autowired
	DocumentoRepository documentRepo;
	
	@Transactional
	public BeneficiarioEntity insert(BeneficiarioEntity postData) {
		if (postData.getDocumentos() != null) {
            for (DocumentoEntity documento : postData.getDocumentos()) {
                documento.setBeneficiario(postData);
            }
        }
		return repo.save(postData);
	}

}
