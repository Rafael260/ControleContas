package com.example.controle_contas.domain;

import java.time.LocalDate;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;

@Entity
@DiscriminatorValue("pessoa_fisica")
@JsonTypeName("pessoa_fisica")
public class PessoaFisica extends Pessoa {

	@JsonProperty("type")
	private final String type = "pessoa_fisica";
	
	@NotNull
	private String cpf;
	
	@NotNull
	private String nomeCompleto;
	
	private LocalDate dataNascimento;
	
	public PessoaFisica() {
		super();
	}
	
	public PessoaFisica(String cpf, String nomeCompleto, LocalDate dataNascimento) {
		super();
		this.cpf = cpf;
		this.nomeCompleto = nomeCompleto;
		this.dataNascimento = dataNascimento;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getNomeCompleto() {
		return nomeCompleto;
	}

	public void setNomeCompleto(String nomeCompleto) {
		this.nomeCompleto = nomeCompleto;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
