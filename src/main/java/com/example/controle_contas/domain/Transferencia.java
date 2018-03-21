package com.example.controle_contas.domain;

public class Transferencia extends Transacao {

	protected Conta contaOrigem;
	
	public Transferencia(Conta contaOrigem, Conta contaEnvolvida, Double valor) {
		super(contaEnvolvida, valor);
		this.contaOrigem = contaOrigem;
	}

	public Conta getContaOrigem() {
		return contaOrigem;
	}

	public void setContaOrigem(Conta contaOrigem) {
		this.contaOrigem = contaOrigem;
	}
	
}
