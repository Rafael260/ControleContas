package com.example.controle_contas.domain;

import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Conta extends AbstractEntity{

	protected String numero;
	
	protected LocalDateTime dataCriacao;
	
	@OneToOne
	@JoinColumn
	protected Pessoa pessoa;
//	protected List<ContaFilial> contasFilhas;
	protected SituacaoConta situacaoConta;
	
	public Conta() {
		this.situacaoConta = SituacaoConta.ATIVA;
	}
	
	public Conta(String numero, Pessoa pessoa) {
		super();
		this.numero = numero;
		this.pessoa = pessoa;
//		this.contasFilhas = new LinkedList<>();
		this.situacaoConta = SituacaoConta.ATIVA;
	}
	
	public String getNumero() {
		return numero;
	}
	public void setNumero(String numero) {
		this.numero = numero;
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
