package com.example.controle_contas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.exceptions.TransacaoInvalidaException;
import com.example.controle_contas.service.CargaService;
import com.example.controle_contas.service.ContaService;

@RestController
@RequestMapping("/cargas")
public class CargaController {

	@Autowired
	private CargaService cargaService;
	
	@Autowired
	private ContaService contaService;
	
	public CargaController() {
	}
	
	@RequestMapping(method = RequestMethod.GET, value="/criar")
	public ResponseEntity<?> carregar(@RequestParam("idConta") Long idConta, @RequestParam("valor") Double valor){
		if(idConta == null || valor == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Conta conta = contaService.findById(idConta);
		if(conta == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			cargaService.carregarConta(conta, valor);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (TransacaoInvalidaException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
