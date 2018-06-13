package com.example.controle_contas.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import com.example.controle_contas.domain.Aporte;
import com.example.controle_contas.domain.Conta;

@Repository
public interface AporteRepository extends AbstractRepository<Aporte>{

	public Optional<Aporte> findByCodigo(String codigo);
	
	public List<Aporte> findByContaEnvolvidaAndContaOrigem(Conta contaEnvolvida, Conta contaOrigem);
}
