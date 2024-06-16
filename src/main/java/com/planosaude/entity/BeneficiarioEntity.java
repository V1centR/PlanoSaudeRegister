package com.planosaude.entity;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;


/**
 * The persistent class for the beneficiario database table.
 * 
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "BENEFICIARIO")
public class BeneficiarioEntity implements Serializable {

	@Id
	private int id;

	private String dataAtualizacao;

	private String dataInclusao;

	private String dataNascimento;

	private String nome;

	private String telefone;

	//bi-directional many-to-one association to Documento
	@OneToMany(mappedBy="beneficiario")
	private List<DocumentoEntity> documentos;


}