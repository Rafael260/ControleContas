package com.example.controle_contas.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue("conta_filial")
@JsonTypeName("conta_filial")
public class ContaFilial extends Conta {

	@JsonProperty("type")
	private final String type = "conta_filial";
	
	@ManyToOne
	private Conta contaPai;
	
	public ContaFilial() {
		super();
	}
	
	public ContaFilial(String nome, Pessoa pessoa) {
		super(nome, pessoa);
		// TODO Auto-generated constructor stub
	}

	public Conta getContaPai() {
		return contaPai;
	}

	public void setContaPai(Conta contaPai) {
		this.contaPai = contaPai;
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
