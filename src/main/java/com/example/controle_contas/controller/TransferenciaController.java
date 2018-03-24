package com.example.controle_contas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.domain.ContaFilial;
import com.example.controle_contas.exceptions.TransacaoInvalidaException;
import com.example.controle_contas.service.ContaFilialService;
import com.example.controle_contas.service.ContaService;
import com.example.controle_contas.service.TransferenciaService;

@RestController
@RequestMapping("/transferencias")
public class TransferenciaController {

	@Autowired
	private TransferenciaService transferenciaService;
	
	@Autowired
	private ContaService contaService;
	
	@Autowired
	private ContaFilialService contaFilialService;
	
	public TransferenciaController() {
		// TODO Auto-generated constructor stub
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/criar")
	public ResponseEntity<?> transferir(@RequestParam("idContaOrigem") Long idContaOrigem, @RequestParam("idContaDestino")  Long idContaDestino , @RequestParam("valor") Double valor){
		if(idContaOrigem == null || idContaDestino == null || valor == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Conta contaOrigem = contaService.findById(idContaOrigem);
		ContaFilial contaDestino = contaFilialService.findById(idContaDestino);
		if(contaOrigem == null || contaDestino == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			transferenciaService.transferir(contaOrigem, contaDestino, valor);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (TransacaoInvalidaException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
