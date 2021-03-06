package com.example.controle_contas.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.controle_contas.controller.dto.TransacaoDTO;
import com.example.controle_contas.domain.Carga;
import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.exceptions.TransacaoInvalidaException;
import com.example.controle_contas.exceptions.TransacaoJaEstornadaException;
import com.example.controle_contas.service.CargaService;
import com.example.controle_contas.service.ContaService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/cargas")
public class CargaController {

	@Autowired
	private CargaService cargaService;

	@Autowired
	private ContaService contaService;

	public CargaController() {
	}

	@RequestMapping(method = RequestMethod.POST, value = "/criar")
	public ResponseEntity<?> carregar(@Valid @RequestBody TransacaoDTO transacaoDTO)
			throws JsonProcessingException {
		Conta conta = contaService.findById(transacaoDTO.getIdConta());
		try {
			Carga carga = cargaService.carregarConta(conta, transacaoDTO.getValor());
			return new ResponseEntity<>(carga, HttpStatus.OK);
		} catch (TransacaoInvalidaException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}

	@RequestMapping(method = RequestMethod.PUT, value = "{idCarga}/estornar")
	public ResponseEntity<?> estornar(@RequestBody Long idCarga) {
		Carga carga = cargaService.findById(idCarga);
		try {
			carga = cargaService.estornarCarga(carga);
			return new ResponseEntity<>(carga, HttpStatus.OK);
		} catch (TransacaoJaEstornadaException e) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
	}
}
