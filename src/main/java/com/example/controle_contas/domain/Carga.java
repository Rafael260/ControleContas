package com.example.controle_contas.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("carga")
public class Carga extends Transacao {

	public Carga() {
		super();
	}
	
	public Carga(Conta contaEnvolvida, Double valor) {
		super(contaEnvolvida, valor);
	}
	
	//toString personalizado

}
