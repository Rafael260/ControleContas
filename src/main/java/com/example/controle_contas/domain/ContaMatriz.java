package com.example.controle_contas.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue("conta_matriz")
@JsonTypeName("conta_matriz")
public class ContaMatriz extends Conta {

	@JsonProperty("type")
	private final String type = "conta_matriz";
	
	public ContaMatriz() {
		super();
	}
	
	public ContaMatriz(String nome, Pessoa pessoa) {
		super(nome, pessoa);
	}
}
