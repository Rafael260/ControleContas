package com.example.controle_contas.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.controle_contas.domain.ContaMatriz;
import com.example.controle_contas.repository.ContaMatrizRepository;

@Service
public class ContaMatrizService extends AbstractService<ContaMatriz>{

	@Autowired
	public ContaMatrizService(ContaMatrizRepository contaMatrizRepository) {
		super(contaMatrizRepository);
	}

}
