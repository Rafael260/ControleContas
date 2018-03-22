package com.example.controle_contas.controller;

import java.time.LocalDateTime;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.service.ContaService;

@RestController
@RequestMapping("/contas")
public class ContaController extends AbstractController<ContaService, Conta>{

	public ContaController() {
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onBeforeInsert(Conta entityToPersist) {
		entityToPersist.setDataCriacao(LocalDateTime.now());
		super.onBeforeInsert(entityToPersist);
	}
}
