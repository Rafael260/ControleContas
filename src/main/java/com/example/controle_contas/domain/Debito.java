package com.example.controle_contas.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue("debito")
@JsonTypeName("debito")
public class Debito extends Transacao {

	@JsonProperty("type")
	private final String type = "debito";
	
	public Debito() {
		super();
	}

	public Debito(Conta contaEnvolvida, Double valor) {
		super(contaEnvolvida, valor);
		// TODO Auto-generated constructor stub
	}

}
