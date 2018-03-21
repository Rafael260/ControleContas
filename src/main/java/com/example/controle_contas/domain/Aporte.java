package com.example.controle_contas.domain;

public class Aporte extends Transferencia {

	private String codigo;
	
	public Aporte(Conta contaOrigem, Conta contaEnvolvida, Double valor, String codigo) {
		super(contaOrigem, contaEnvolvida, valor);
		this.codigo = codigo;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	
}
