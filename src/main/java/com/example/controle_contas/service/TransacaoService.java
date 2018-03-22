package com.example.controle_contas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controle_contas.domain.Transacao;
import com.example.controle_contas.repository.TransacaoRepository;

@Service
public class TransacaoService extends AbstractService<Transacao> {

	@Autowired
	public TransacaoService(TransacaoRepository<Transacao> repositorio) {
		super(repositorio);
	}

}
