package com.planosaude.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.planosaude.enums.DocumentsTypeEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;


/**
 * The persistent class for the documento database table.
 * 
 */
@Getter @Setter
@Entity
@Table(name = "DOCUMENTO")
public class DocumentoEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "doc_sequence")
	private int id;

	private String dataAtualizacao;

	private String dataInclusao;

	private String descricao;
	
	@Enumerated(EnumType.STRING)
    private DocumentsTypeEnum tipoDocumento;

	//bi-directional many-to-one association to Beneficiario
	@ManyToOne
    @JoinColumn(name = "idBeneficiario", referencedColumnName = "id")
	@JsonBackReference
	private BeneficiarioEntity beneficiario;
}