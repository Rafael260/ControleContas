package com.example.controle_contas.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("conta_matriz")
public class ContaMatriz extends Conta {

	public ContaMatriz() {
		super();
	}
	
	public ContaMatriz(String nome, Pessoa pessoa) {
		super(nome, pessoa);
	}
}
