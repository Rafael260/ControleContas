package com.example.controle_contas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controle_contas.domain.PessoaJuridica;
import com.example.controle_contas.repository.PessoaJuridicaRepository;

@Service
public class PessoaJuridicaService extends AbstractService<PessoaJuridica>{

	@Autowired
	public PessoaJuridicaService(PessoaJuridicaRepository repositorio) {
		super(repositorio);
		// TODO Auto-generated constructor stub
	}

	
}
