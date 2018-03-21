package com.example.controle_contas.domain;

import java.time.LocalDateTime;

public class ContaMatriz extends Conta {

	public ContaMatriz(String nome, LocalDateTime dataCriacao, Pessoa pessoa) {
		super(nome, dataCriacao, pessoa);
	}

}
