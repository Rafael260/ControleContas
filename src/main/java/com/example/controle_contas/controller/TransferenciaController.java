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

import com.example.controle_contas.controller.dto.TransferenciaDTO;
import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.domain.ContaFilial;
import com.example.controle_contas.domain.Transferencia;
import com.example.controle_contas.exceptions.TransacaoInvalidaException;
import com.example.controle_contas.exceptions.TransacaoJaEstornadaException;
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

	public TransferenciaController() {
	}

	@RequestMapping(method = RequestMethod.POST, value = "/criar")
	public ResponseEntity<?> transferir(@Valid @RequestBody TransferenciaDTO transferenciaDTO) 
			throws JsonProcessingException {
		Conta contaOrigem = contaService.findById(transferenciaDTO.getIdContaOrigem());
		ContaFilial contaDestino = contaFilialService.findById(transferenciaDTO.getIdContaDestino());
		try {
			Transferencia transferencia = transferenciaService.transferir(contaOrigem, contaDestino, 
					transferenciaDTO.getValor());
			return new ResponseEntity<>(transferencia, HttpStatus.OK);
		} catch (TransacaoInvalidaException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{idTransferencia}/estornar")
	public ResponseEntity<?> estornar(@PathVariable Long idTransferencia) {
		Transferencia transferencia = transferenciaService.findById(idTransferencia);
		try {
			transferencia = transferenciaService.estornarTransferencia(transferencia);
			return new ResponseEntity<>(transferencia, HttpStatus.OK);
		} catch (TransacaoJaEstornadaException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
