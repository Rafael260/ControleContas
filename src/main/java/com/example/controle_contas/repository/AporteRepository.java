package com.example.controle_contas.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.controle_contas.domain.Aporte;
import com.example.controle_contas.domain.Conta;

@Repository
public interface AporteRepository extends AbstractRepository<Aporte>{

	//Se isso nao funcionar, tem q retornar a lista e tratar no service
	public Aporte findByCodigo(String codigo);
	
	public List<Aporte> findByContaEnvolvidaAndContaOrigem(Conta contaEnvolvida, Conta contaOrigem);
}
