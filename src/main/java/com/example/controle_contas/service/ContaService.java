package com.example.controle_contas.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.repository.ContaRepository;

@Service
public class ContaService extends AbstractService<Conta>{

	@Autowired
	public ContaService(ContaRepository<Conta> repositorio) {
		super(repositorio);
	}
	
	@Override
	public void onBeforeInsert(Conta entityToPersist) {
		entityToPersist.setDataCriacao(LocalDateTime.now());
		super.onBeforeInsert(entityToPersist);
	}
}
