package com.example.controle_contas.domain;

import java.time.LocalDateTime;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import org.springframework.data.annotation.CreatedDate;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_transacao")
public abstract class Transacao extends AbstractEntity {

	@NotNull
	protected Double valor;
	
	@ManyToOne
	protected Conta contaEnvolvida;
	
	@CreatedDate
	protected LocalDateTime data;
	
	protected boolean estornada;
	
	public Transacao() {
		estornada = false;
	}
	
	public Transacao(Conta contaEnvolvida, Double valor) {
		this.valor = valor;
		this.contaEnvolvida = contaEnvolvida;
		estornada = false;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Conta getContaEnvolvida() {
		return contaEnvolvida;
	}

	public void setContaEnvolvida(Conta contaEnvolvida) {
		this.contaEnvolvida = contaEnvolvida;
	}

	public LocalDateTime getData() {
		return data;
	}

	public void setData(LocalDateTime data) {
		this.data = data;
	}

	public boolean isEstornada() {
		return estornada;
	}

	public void setEstornada(boolean estornada) {
		this.estornada = estornada;
	}

}
