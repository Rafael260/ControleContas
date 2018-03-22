package com.example.controle_contas.repository;

import org.springframework.stereotype.Repository;

import com.example.controle_contas.domain.Pessoa;

@Repository
public interface PessoaRepository<E extends Pessoa> extends AbstractRepository<E>{

}
