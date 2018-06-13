package com.example.controle_contas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.controle_contas.controller.dto.TransacaoDTO;
import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.domain.Debito;
import com.example.controle_contas.exceptions.TransacaoInvalidaException;
import com.example.controle_contas.exceptions.TransacaoJaEstornadaException;
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

	public DebitoController() {
	}

	@RequestMapping(method = RequestMethod.POST, value = "/criar")
	public ResponseEntity<?> debitar(@Valid @RequestBody TransacaoDTO transacaoDTO)
			throws JsonProcessingException {
		Conta conta = contaService.findById(transacaoDTO.getIdConta());
		try {
			Debito debito = debitoService.debitarConta(conta, transacaoDTO.getValor());
			return new ResponseEntity<>(debito, HttpStatus.OK);
		} catch (TransacaoInvalidaException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{idDebito}/estornar")
	public ResponseEntity<?> estornar(@PathVariable Long idDebito) {
		Debito debito = debitoService.findById(idDebito);
		try {
			debito = debitoService.estornarDebito(debito);
			return new ResponseEntity<>(debito, HttpStatus.OK);
		} catch (TransacaoJaEstornadaException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
