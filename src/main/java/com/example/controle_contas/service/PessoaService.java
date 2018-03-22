package com.example.controle_contas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controle_contas.domain.Pessoa;
import com.example.controle_contas.repository.PessoaRepository;

@Service
public class PessoaService extends AbstractService<Pessoa> {

	@Autowired
	public PessoaService(PessoaRepository<Pessoa> repositorio) {
		super(repositorio);
	}

	
}
