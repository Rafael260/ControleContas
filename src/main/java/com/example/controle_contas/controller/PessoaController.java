package com.example.controle_contas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controle_contas.domain.Pessoa;
import com.example.controle_contas.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController extends AbstractController<PessoaService, Pessoa>{

	public PessoaController() {
	}
}
