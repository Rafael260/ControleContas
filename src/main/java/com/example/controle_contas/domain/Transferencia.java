package com.example.controle_contas.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue("transferencia")
@JsonTypeName("transferencia")
public class Transferencia extends Transacao {

	@JsonProperty("type")
	private final String type = "transferencia";
	
	@ManyToOne
	protected Conta contaOrigem;
	
	public Transferencia() {
		super();
	}
	
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
