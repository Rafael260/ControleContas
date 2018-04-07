package com.example.controle_contas.domain;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("aporte")
@JsonTypeName("aporte")
@NoArgsConstructor
public class Aporte extends Transferencia {

	@JsonProperty("type")
	private final String type = "aporte";
	
	@Column(unique = true, nullable = false)
	@Getter
	@Setter
	private String codigo;
	
	public Aporte(Conta contaOrigem, Conta contaEnvolvida, Double valor, String codigo) {
		super(contaOrigem, contaEnvolvida, valor);
		this.codigo = codigo;
	}
}
