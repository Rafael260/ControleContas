package com.example.controle_contas.domain;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;

@Entity
public class PessoaFisica extends Pessoa {

	@NotNull
	private String cpf;
	
	@NotNull
	private String nomeCompleto;
	
	private LocalDateTime dataNascimento;
	
	public PessoaFisica(String cpf, String nomeCompleto, LocalDateTime dataNascimento) {
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

	public LocalDateTime getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDateTime dataNascimento) {
		this.dataNascimento = dataNascimento;
	}
	
}
