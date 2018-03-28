package com.example.controle_contas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.controle_contas.domain.Aporte;
import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.domain.ContaMatriz;
import com.example.controle_contas.exceptions.TransacaoInvalidaException;
import com.example.controle_contas.exceptions.TransacaoJaEstornadaException;
import com.example.controle_contas.service.AporteService;
import com.example.controle_contas.service.ContaMatrizService;
import com.example.controle_contas.service.ContaService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/aportes")
public class AporteController {

	@Autowired
	private AporteService aporteService;

	@Autowired
	private ContaService contaService;

	@Autowired
	private ContaMatrizService contaMatrizService;

	public AporteController() {
	}

	@RequestMapping(method = RequestMethod.POST, value = "/criar")
	public ResponseEntity<?> criar(@RequestBody Long idContaOrigem, @RequestBody Long idContaDestino,
			@RequestBody Double valor) throws JsonProcessingException {
		if (idContaOrigem == null || idContaDestino == null || valor == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Conta contaOrigem = contaService.findById(idContaOrigem);
		ContaMatriz contaDestino = contaMatrizService.findById(idContaDestino);
		if (contaOrigem == null || contaDestino == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			Aporte aporte = aporteService.enviarAporte(contaOrigem, contaDestino, valor);
			return new ResponseEntity<>(aporte, HttpStatus.OK);
		} catch (TransacaoInvalidaException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

	@RequestMapping(method = RequestMethod.PUT, value = "/estornar")
	public ResponseEntity<?> estornar(@RequestBody String codigoAporte) {
		if (codigoAporte == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Aporte aporte = aporteService.findByCodigo(codigoAporte);
		if (aporte == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		try {
			aporte = aporteService.estornarAporte(aporte);
			return new ResponseEntity<>(aporte, HttpStatus.OK);
		} catch (TransacaoJaEstornadaException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}

	}

}
