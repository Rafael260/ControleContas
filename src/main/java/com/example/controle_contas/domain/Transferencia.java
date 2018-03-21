package com.example.controle_contas.domain;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public class Transferencia extends Transacao {

	@ManyToOne
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
