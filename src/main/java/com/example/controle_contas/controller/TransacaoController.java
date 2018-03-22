package com.example.controle_contas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controle_contas.domain.Transacao;
import com.example.controle_contas.service.TransacaoService;

@RestController
@RequestMapping("/transacoes")
public class TransacaoController extends AbstractController<TransacaoService, Transacao>{

	public TransacaoController() {
		// TODO Auto-generated constructor stub
	}

}
