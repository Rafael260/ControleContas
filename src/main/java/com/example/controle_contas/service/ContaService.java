package com.example.controle_contas.service;

import java.time.LocalDate;

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
		entityToPersist.setDataCriacao(LocalDate.now());
		super.onBeforeInsert(entityToPersist);
	}
}
