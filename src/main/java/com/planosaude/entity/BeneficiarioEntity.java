package com.planosaude.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the beneficiario database table.
 * 
 */
@Getter @Setter
@Entity
@Table(name = "BENEFICIARIO")
public class BeneficiarioEntity {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private String dataAtualizacao;

	private String dataInclusao;

	private String dataNascimento;

	private String nome;

	private String telefone;

	@OneToMany(mappedBy = "beneficiario", cascade = CascadeType.ALL, orphanRemoval = true)
	@JsonManagedReference
	private List<DocumentoEntity> documentos;
	
	public DocumentoEntity addDocumento(DocumentoEntity documento) {
		getDocumentos().add(documento);
		documento.setBeneficiario(this);

		return documento;
	}

	public DocumentoEntity removeDocumento(DocumentoEntity documento) {
		getDocumentos().remove(documento);
		documento.setBeneficiario(null);

		return documento;
	}


}