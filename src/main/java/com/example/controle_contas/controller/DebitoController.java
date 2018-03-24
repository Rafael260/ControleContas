package com.example.controle_contas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.controle_contas.controller.util.GeradorJson;
import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.domain.Debito;
import com.example.controle_contas.exceptions.TransacaoInvalidaException;
import com.example.controle_contas.service.ContaService;
import com.example.controle_contas.service.DebitoService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/debitos")
public class DebitoController {

	@Autowired
	private ContaService contaService;
	
	@Autowired
	private DebitoService debitoService;
	
	GeradorJson geradorJson;
	
	public DebitoController() {
		geradorJson = new GeradorJson();
	}

	@RequestMapping(method = RequestMethod.GET, value="/criar")
	public ResponseEntity<?> debitar(@RequestParam("idConta") Long idConta, @RequestParam("valor") Double valor) throws JsonProcessingException{
		if(idConta == null || valor == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Conta conta = contaService.findById(idConta);
		if(conta == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			Debito debito = debitoService.debitarConta(conta, valor);
			return new ResponseEntity<>(geradorJson.gerarJson(debito),HttpStatus.OK);
		} catch (TransacaoInvalidaException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
