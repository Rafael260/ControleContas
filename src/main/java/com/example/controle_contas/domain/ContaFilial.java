package com.example.controle_contas.domain;

import java.time.LocalDateTime;

public class ContaFilial extends Conta {

	private Conta contaPai;
	
	public ContaFilial(String nome, LocalDateTime dataCriacao, Pessoa pessoa) {
		super(nome, dataCriacao, pessoa);
		// TODO Auto-generated constructor stub
	}

	public Conta getContaPai() {
		return contaPai;
	}

	public void setContaPai(Conta contaPai) {
		this.contaPai = contaPai;
	}
	
	public ContaMatriz coletarContaMatriz() {
		return coletarContaMatriz(this);
	}
	
	private ContaMatriz coletarContaMatriz(ContaFilial contaFilial) {
		Conta contaPai = contaFilial.getContaPai();
		if(contaPai instanceof ContaMatriz) {
			return (ContaMatriz)contaPai;
		}
		else {
			return coletarContaMatriz((ContaFilial)contaPai);
		}
	}

}
