package com.example.controle_contas.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("transferencia")
@JsonTypeName("transferencia")
@NoArgsConstructor
public class Transferencia extends Transacao {

	@JsonProperty("type")
	private final String type = "transferencia";
	
	@ManyToOne
	@Getter
	@Setter
	protected Conta contaOrigem;
	
	public Transferencia(Conta contaOrigem, Conta contaEnvolvida, Double valor) {
		super(contaEnvolvida, valor);
		this.contaOrigem = contaOrigem;
	}
}
