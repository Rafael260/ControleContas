package com.example.controle_contas.domain;

import javax.persistence.Entity;

@Entity
public class ContaMatriz extends Conta {

	public ContaMatriz(String nome, Pessoa pessoa) {
		super(nome, pessoa);
	}

}
