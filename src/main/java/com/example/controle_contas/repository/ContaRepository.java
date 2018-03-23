package com.example.controle_contas.repository;


import org.springframework.stereotype.Repository;

import com.example.controle_contas.domain.Conta;

@Repository
public interface ContaRepository<E extends Conta> extends AbstractRepository<E>{

}
