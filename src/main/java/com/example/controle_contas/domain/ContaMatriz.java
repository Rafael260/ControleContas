package com.example.controle_contas.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("conta_matriz")
@JsonTypeName("conta_matriz")
@NoArgsConstructor
public class ContaMatriz extends Conta {

	@JsonProperty("type")
	private final String type = "conta_matriz";
	
	public ContaMatriz(String nome, Pessoa pessoa) {
		super(nome, pessoa);
	}
}
