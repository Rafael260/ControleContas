package com.example.controle_contas.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("debito")
public class Debito extends Transacao {

	public Debito() {
		super();
	}

	public Debito(Conta contaEnvolvida, Double valor) {
		super(contaEnvolvida, valor);
		// TODO Auto-generated constructor stub
	}

}
