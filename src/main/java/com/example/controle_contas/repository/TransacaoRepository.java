package com.example.controle_contas.repository;


import org.springframework.stereotype.Repository;

import com.example.controle_contas.domain.Transacao;

@Repository
public interface TransacaoRepository<E extends Transacao> extends AbstractRepository<E> {

}
