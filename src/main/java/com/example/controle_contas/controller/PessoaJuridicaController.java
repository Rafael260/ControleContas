package com.example.controle_contas.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controle_contas.domain.PessoaJuridica;
import com.example.controle_contas.service.PessoaJuridicaService;

@RestController
@RequestMapping("/pessoasJuridicas")
public class PessoaJuridicaController extends AbstractController<PessoaJuridicaService, PessoaJuridica>{

	public PessoaJuridicaController() {
		// TODO Auto-generated constructor stub
	}

}
