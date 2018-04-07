package com.example.controle_contas.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("debito")
@JsonTypeName("debito")
@NoArgsConstructor
public class Debito extends Transacao {

	@JsonProperty("type")
	private final String type = "debito";
	
	public Debito(Conta contaEnvolvida, Double valor) {
		super(contaEnvolvida, valor);
	}
}
