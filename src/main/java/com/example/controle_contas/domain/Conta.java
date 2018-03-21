package com.example.controle_contas.domain;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_conta")
public abstract class Conta {

	@Id
	@GeneratedValue
	protected Long id;
	
	protected String numero;
	
	@CreatedDate 
	protected LocalDateTime dataCriacao;
	
	@OneToOne
	@JoinColumn
	protected Pessoa pessoa;
//	protected List<ContaFilial> contasFilhas;
	protected SituacaoConta situacaoConta;
	
	public Conta(String numero, Pessoa pessoa) {
		super();
		this.numero = numero;
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
