package com.example.controle_contas;

import java.io.IOException;

public class TesteConsumo {

	public TesteConsumo() {
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		ClientRest client = new ClientRest();
		try {
			client.imprimirContas();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
