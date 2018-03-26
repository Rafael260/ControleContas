package com.example.controle_contas;

import java.io.IOException;
import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.example.controle_contas.domain.Conta;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;

public class ClientRest {

	private RestTemplate restTemplate;
	
	public ClientRest() {
		restTemplate = new RestTemplate();
	}
	
	public void imprimirContas() throws JsonParseException, JsonMappingException, IOException {
		ResponseEntity<List<Conta>> response =
		        restTemplate.exchange("http://localhost:8080/contas/all",
		                    HttpMethod.GET, null, new ParameterizedTypeReference<List<Conta>>() {
		            });
		List<Conta> contas = response.getBody();
		for (Conta conta : contas) {
			System.out.println("Id da conta: " + conta.getId());
			System.out.println("Saldo da conta: " + conta.getSaldo());
			System.out.println();
		}
	}

}
