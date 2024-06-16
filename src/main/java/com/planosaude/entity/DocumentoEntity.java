package com.planosaude.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


/**
 * The persistent class for the documento database table.
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "DOCUMENTO")
public class DocumentoEntity implements Serializable {

	@Id
	private int id;

	private String dataAtualizacao;

	private String dataInclusao;

	private String descricao;

	private String tipoDocumento;

	//bi-directional many-to-one association to Beneficiario
	@ManyToOne
	@JoinColumn(name="idBeneficiario")
	private BeneficiarioEntity beneficiario;
}