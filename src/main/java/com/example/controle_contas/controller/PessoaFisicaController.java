package com.example.controle_contas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controle_contas.domain.PessoaFisica;
import com.example.controle_contas.service.PessoaFisicaService;

@RestController
@RequestMapping("/pessoasFisicas")
public class PessoaFisicaController extends AbstractController<PessoaFisicaService, PessoaFisica>{

	public PessoaFisicaController() {
		// TODO Auto-generated constructor stub
	}

}
