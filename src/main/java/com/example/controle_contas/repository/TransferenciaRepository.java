package com.example.controle_contas.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.domain.Transferencia;

@Repository
public interface TransferenciaRepository extends AbstractRepository<Transferencia>{

	List<Transferencia> findByContaOrigem(Conta contaOrigem);
}
