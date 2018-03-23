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

@RestController
@RequestMapping("/contas")
public class ContaController extends AbstractController<ContaService, Conta>{

	@Autowired
	ContaFilialService contaFilialService;
	
	public ContaController() {
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/carregar")
	public ResponseEntity<?> carregar(@RequestParam("idConta") Long idConta, @RequestParam("valor") Double valor){
		if(idConta == null || valor == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Conta conta = service.findById(idConta);
		if(conta == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			service.carregarConta(conta, valor);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (TransacaoInvalidaException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/transferir")
	public ResponseEntity<?> transferir(@RequestParam("idContaOrigem") Long idContaOrigem, @RequestParam("idContaDestino")  Long idContaDestino , @RequestParam("valor") Double valor){
		if(idContaOrigem == null || idContaDestino == null || valor == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Conta contaOrigem = service.findById(idContaOrigem);
		ContaFilial contaDestino = contaFilialService.findById(idContaDestino);
		if(contaOrigem == null || contaDestino == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			service.transferir(contaOrigem, contaDestino, valor);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (TransacaoInvalidaException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
