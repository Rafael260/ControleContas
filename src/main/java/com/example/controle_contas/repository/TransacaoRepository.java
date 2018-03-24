package com.example.controle_contas.repository;


import java.util.List;

import org.springframework.stereotype.Repository;

import com.example.controle_contas.domain.Conta;
import com.example.controle_contas.domain.Transacao;

@Repository
public interface TransacaoRepository extends AbstractRepository<Transacao> {

	List<Transacao> findByContaEnvolvida(Conta contaEnvolvida);
}
