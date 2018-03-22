package com.example.controle_contas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controle_contas.service.PessoaService;

@RestController
@RequestMapping("/pessoas")
public class PessoaController {

	@Autowired
	PessoaService pessoaService;
	
	public PessoaController() {
		// TODO Auto-generated constructor stub
	}

}
