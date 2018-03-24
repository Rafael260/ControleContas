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
import com.example.controle_contas.domain.ContaFilial;
import com.example.controle_contas.domain.Transferencia;
import com.example.controle_contas.exceptions.TransacaoInvalidaException;
import com.example.controle_contas.service.ContaFilialService;
import com.example.controle_contas.service.ContaService;
import com.example.controle_contas.service.TransferenciaService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

	@Autowired
	private TransferenciaService transferenciaService;
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private ContaFilialService contaFilialService;
	
	GeradorJson geradorJson;
	
	public TransferenciaController() {
		geradorJson = new GeradorJson();
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/criar")
	public ResponseEntity<?> transferir(@RequestParam("idContaOrigem") Long idContaOrigem, 
			@RequestParam("idContaDestino")  Long idContaDestino , @RequestParam("valor") Double valor) throws JsonProcessingException{
		if(idContaOrigem == null || idContaDestino == null || valor == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Conta contaOrigem = contaService.findById(idContaOrigem);
		ContaFilial contaDestino = contaFilialService.findById(idContaDestino);
		if(contaOrigem == null || contaDestino == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			Transferencia transferencia = transferenciaService.transferir(contaOrigem, contaDestino, valor);
			return new ResponseEntity<>(geradorJson.gerarJson(transferencia),HttpStatus.OK);
		} catch (TransacaoInvalidaException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
