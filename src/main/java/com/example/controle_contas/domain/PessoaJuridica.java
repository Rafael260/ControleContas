package com.example.controle_contas.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue("pessoa_juridica")
@JsonTypeName("pessoa_juridica")
public class PessoaJuridica extends Pessoa {

	@JsonProperty("type")
	private final String type = "pessoa_juridica";
	
	@NotNull
	private String cnpj;
	
	@NotNull
	private String razaoSocial;
	
	@NotNull
	private String nomeFantasia;
	
	public PessoaJuridica() {
		super();
	}
	
	public PessoaJuridica(String cnpj, String razaoSocial, String nomeFantasia) {
		super();
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

	public String getRazaoSocial() {
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia() {
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia) {
		this.nomeFantasia = nomeFantasia;
	}
	
}
