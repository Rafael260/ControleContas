package com.example.controle_contas.service;

import java.time.LocalDateTime;

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
	
	@Override
	public void onBeforeInsert(Transacao entityToPersist) {
		entityToPersist.setData(LocalDateTime.now());
		super.onBeforeInsert(entityToPersist);
	}
}
