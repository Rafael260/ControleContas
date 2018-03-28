package com.example.controle_contas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.domain.Transacao;
import com.example.controle_contas.service.ContaService;
import com.example.controle_contas.service.TransacaoService;
import com.fasterxml.jackson.core.JsonProcessingException;

@RestController
@RequestMapping("/contas")
public class ContaController extends AbstractController<ContaService, Conta> {

	@Autowired
	private TransacaoService transacaoService;

	public ContaController() {
	}

	@RequestMapping(method = RequestMethod.GET, value = "/{id}/historico")
	public ResponseEntity<?> coletarHistorico(@PathVariable("id") Long idConta) throws JsonProcessingException {
		if (idConta == null) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		Conta conta = service.findById(idConta);
		if (conta == null) {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
		List<Transacao> historicoDaConta = transacaoService.coletarHistoricoDaConta(conta);
		return new ResponseEntity<>(historicoDaConta, HttpStatus.OK);
	}
}
