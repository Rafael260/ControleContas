package com.example.controle_contas.client_rest;

import java.util.List;

import org.springframework.web.client.RestTemplate;

import com.example.controle_contas.domain.Conta;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;


public class ClientRest {

	private RestTemplate restTemplate;
	
	public ClientRest() {
		restTemplate = new RestTemplate();
	}
	
	public void imprimirContas() {
		List<Conta> listaContas = restTemplate.getForObject("http://localhost:8080/contas/all", List.class);
		for(Conta conta: listaContas) {
			System.out.println("Id conta: " + conta.getId());
			System.out.println("Saldo: " + conta.getSaldo());
			System.out.println();
		}
	}

}
