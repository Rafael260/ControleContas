package com.example.controle_contas.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.NoArgsConstructor;

@Entity
@DiscriminatorValue("carga")
@JsonTypeName("carga")
@NoArgsConstructor
public class Carga extends Transacao {

	@JsonProperty("type")
	private final String type = "carga";
	
	public Carga(Conta contaEnvolvida, Double valor) {
		super(contaEnvolvida, valor);
	}
}
