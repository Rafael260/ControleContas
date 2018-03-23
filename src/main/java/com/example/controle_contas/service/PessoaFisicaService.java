package com.example.controle_contas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controle_contas.domain.PessoaFisica;
import com.example.controle_contas.repository.PessoaFisicaRepository;

@Service
public class PessoaFisicaService extends AbstractService<PessoaFisica>{

	@Autowired
	public PessoaFisicaService(PessoaFisicaRepository pessoaFisicaRespository) {
		super(pessoaFisicaRespository);
	}

}
