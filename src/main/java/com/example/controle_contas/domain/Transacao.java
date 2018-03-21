package com.example.controle_contas.domain;

import java.time.LocalDateTime;

public abstract class Transacao {

	protected Double valor;
	protected Conta contaEnvolvida;
	protected LocalDateTime data;
	protected boolean estornada;
	
	public Transacao(Conta contaEnvolvida, Double valor) {
		this.valor = valor;
		this.contaEnvolvida = contaEnvolvida;
		data = LocalDateTime.now();
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
