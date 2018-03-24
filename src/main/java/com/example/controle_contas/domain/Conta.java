package com.example.controle_contas.domain;

import java.time.LocalDate;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;

@JsonTypeInfo(
		  use = JsonTypeInfo.Id.NAME, 
		  include = JsonTypeInfo.As.PROPERTY, 
		  property = "type")
		@JsonSubTypes({ 
		  @Type(value = ContaMatriz.class, name = "conta_matriz"), 
		  @Type(value = ContaFilial.class, name = "conta_filial")
		})
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name="tipo_conta")
public abstract class Conta extends AbstractEntity{

	protected String nome;
	protected Double saldo;
	protected LocalDate dataCriacao;
	
	@OneToOne
	@JoinColumn
	protected Pessoa pessoa;
	
	protected SituacaoConta situacaoConta;
	
	public Conta() {
		super();
		this.saldo = 0.0;
		this.situacaoConta = SituacaoConta.ATIVA;
	}
	
	public Conta(String numero, Pessoa pessoa) {
		super();
		this.saldo = 0.0;
		this.nome = numero;
		this.pessoa = pessoa;
		this.situacaoConta = SituacaoConta.ATIVA;
	}
	
	public Double getSaldo() {
		return saldo;
	}

	public void setSaldo(Double saldo) {
		this.saldo = saldo;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public LocalDate getDataCriacao() {
		return dataCriacao;
	}
	public void setDataCriacao(LocalDate dataCriacao) {
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
	
	@Override
	public boolean equals(Object obj) {
		if(obj == null || !(obj instanceof ContaMatriz || obj instanceof ContaFilial)) {
			return false;
		}
		Conta outraConta = (Conta) obj;
		return this.id.equals(outraConta.getId());
	}
	
}
