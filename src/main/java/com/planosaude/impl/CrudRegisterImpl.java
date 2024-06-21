package com.planosaude.impl;

import java.util.List;
import java.util.Optional;

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
	
	@Transactional
	public List<BeneficiarioEntity> getAll() {
		return repo.findAll();
	}
	
	@Transactional
	public List<DocumentoEntity> getAllDocs(String idBeneficiario) {
		return documentRepo.findByBeneficiarioId(Integer.valueOf(idBeneficiario));
	}
	
	@Transactional
    public Optional<BeneficiarioEntity> updateBeneficiario(int id, BeneficiarioEntity beneficiarioDetails) {
        Optional<BeneficiarioEntity> optionalBeneficiario = repo.findById(id);
        if (optionalBeneficiario.isPresent()) {
            BeneficiarioEntity existingBeneficiario = optionalBeneficiario.get();
            existingBeneficiario.setDataAtualizacao(beneficiarioDetails.getDataAtualizacao());
            existingBeneficiario.setDataInclusao(beneficiarioDetails.getDataInclusao());
            existingBeneficiario.setDataNascimento(beneficiarioDetails.getDataNascimento());
            existingBeneficiario.setNome(beneficiarioDetails.getNome());
            existingBeneficiario.setTelefone(beneficiarioDetails.getTelefone());
            
            List<DocumentoEntity> existingDocuments = existingBeneficiario.getDocumentos();
            existingDocuments.clear();
            for (DocumentoEntity documento : beneficiarioDetails.getDocumentos()) {
                documento.setBeneficiario(existingBeneficiario);
                existingDocuments.add(documento);
            }
            existingBeneficiario.setDocumentos(existingDocuments);

            repo.save(existingBeneficiario);
            return Optional.of(existingBeneficiario);
        }
        return Optional.empty();
    }

}
