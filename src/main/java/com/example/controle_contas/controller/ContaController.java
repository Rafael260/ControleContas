package com.example.controle_contas.controller;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.domain.ContaMatriz;
import com.example.controle_contas.domain.Pessoa;
import com.example.controle_contas.domain.PessoaFisica;

@RestController
@RequestMapping("/contas")
public class ContaController {

	public ContaController() {
		// TODO Auto-generated constructor stub
	}

	@GetMapping(value="/all")
	public List<Conta> listar(){
		List<Conta> contas = new LinkedList<>();
		Pessoa pessoaParaConta = new PessoaFisica("11111111111","Fulano de tal",LocalDateTime.now());
		Conta conta = new ContaMatriz("1668-3;42773-X", pessoaParaConta);
		contas.add(conta);
		return contas;
	}
}
