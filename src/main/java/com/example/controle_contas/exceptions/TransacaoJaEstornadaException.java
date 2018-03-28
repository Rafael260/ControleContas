package com.example.controle_contas.exceptions;

public class TransacaoJaEstornadaException extends Exception{

	private static final long serialVersionUID = -6109103504885554814L;

	public TransacaoJaEstornadaException(String msg) {
		super(msg);
	}

}
