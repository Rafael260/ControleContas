package com.example.controle_contas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controle_contas.domain.ContaFilial;
import com.example.controle_contas.repository.ContaFilialRepository;

@Service
public class ContaFilialService extends AbstractService<ContaFilial>{

	@Autowired
	public ContaFilialService(ContaFilialRepository repository) {
		super(repository);
	}

}
