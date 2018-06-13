package com.example.controle_contas;

import java.io.IOException;

public class TesteConsumo {

	public TesteConsumo() {
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
