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
	public ResponseEntity<?> criar(@Valid @RequestBody TransferenciaDTO transferenciaDTO) throws JsonProcessingException {
		Conta contaOrigem = contaService.findById(transferenciaDTO.getIdContaOrigem());
		ContaMatriz contaDestino = contaMatrizService.findById(transferenciaDTO.getIdContaDestino());
		try {
			Aporte aporte = aporteService.enviarAporte(contaOrigem, contaDestino, 
							transferenciaDTO.getValor());
			return new ResponseEntity<>(aporte, HttpStatus.OK);
		} catch (TransacaoInvalidaException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{codigoAporte}/estornar")
	public ResponseEntity<?> estornar(@PathVariable String codigoAporte) {
		Aporte aporte = aporteService.findByCodigo(codigoAporte);
		try {
			aporte = aporteService.estornarAporte(aporte);
			return new ResponseEntity<>(aporte, HttpStatus.OK);
		} catch (TransacaoJaEstornadaException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
