package com.example.controle_contas.domain;

import java.time.LocalDateTime;

public abstract class Conta {

	protected Long id;
	protected String nome;
	protected LocalDateTime dataCriacao;
	protected Pessoa pessoa;
//	protected List<ContaFilial> contasFilhas;
	protected SituacaoConta situacaoConta;
	
	public Conta(String nome, LocalDateTime dataCriacao, Pessoa pessoa) {
		super();
		this.nome = nome;
		this.dataCriacao = dataCriacao;
		this.pessoa = pessoa;
//		this.contasFilhas = new LinkedList<>();
		this.situacaoConta = SituacaoConta.ATIVA;
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDateTime getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDateTime dataCriacao) {
		this.dataCriacao = dataCriacao;
	}
	public Pessoa getPessoa() {
		return pessoa;
	}
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public SituacaoConta getSituacaoConta() {
		return situacaoConta;
	}

	public void setSituacaoConta(SituacaoConta situacaoConta) {
		this.situacaoConta = situacaoConta;
	}
}
