package com.example.controle_contas.domain;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_conta")
public abstract class Conta extends AbstractEntity{

	protected String numero;
	protected Double saldo;
	protected LocalDateTime dataCriacao;
	
	@OneToOne
	@JoinColumn
	protected Pessoa pessoa;
//	protected List<ContaFilial> contasFilhas;
	protected SituacaoConta situacaoConta;
	
	public Conta() {
		this.saldo = 0.0;
		this.situacaoConta = SituacaoConta.ATIVA;
	}
	
	public Conta(String numero, Pessoa pessoa) {
		super();
		this.saldo = 0.0;
		this.numero = numero;
		this.pessoa = pessoa;
//		this.contasFilhas = new LinkedList<>();
		this.situacaoConta = SituacaoConta.ATIVA;
	}
	
	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
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
	
	public void acrescentarValor(Double valor) {
		this.saldo += valor;
	}
	
	public void decrementarValor(Double valor) {
		this.saldo -= valor;
	}
	
	public boolean estaAtiva() {
		return this.situacaoConta.equals(SituacaoConta.ATIVA);
	}
	
}
