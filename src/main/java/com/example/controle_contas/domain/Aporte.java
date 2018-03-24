package com.example.controle_contas.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("aporte")
public class Aporte extends Transferencia {

	@Column(unique = true, nullable = false)
	private String codigo;
	
	public Aporte() {
		
	}
	
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
