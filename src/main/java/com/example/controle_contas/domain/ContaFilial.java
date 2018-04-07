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
@DiscriminatorValue("conta_filial")
@JsonTypeName("conta_filial")
@NoArgsConstructor
public class ContaFilial extends Conta {

	@JsonProperty("type")
	private final String type = "conta_filial";
	
	@ManyToOne
	@Getter
	@Setter
	private Conta contaPai;
	
	public ContaFilial(String nome, Pessoa pessoa) {
		super(nome, pessoa);
	}

	public ContaMatriz coletarContaMatriz() {
		return coletarContaMatriz(this);
	}
	
	private ContaMatriz coletarContaMatriz(ContaFilial contaFilial) {
		Conta contaPai = contaFilial.getContaPai();
		if(contaPai instanceof ContaMatriz) {
			return (ContaMatriz)contaPai;
		}
		else {
			return coletarContaMatriz((ContaFilial)contaPai);
		}
	}
}
