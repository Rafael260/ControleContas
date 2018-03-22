package com.example.controle_contas.repository;

import org.springframework.stereotype.Repository;

import com.example.controle_contas.domain.Transferencia;

@Repository
public interface TransferenciaRepository extends TransacaoRepository<Transferencia>{

}
