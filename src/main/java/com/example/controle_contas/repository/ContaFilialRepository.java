package com.example.controle_contas.repository;

import org.springframework.transaction.annotation.Transactional;

import com.example.controle_contas.domain.ContaFilial;

@Transactional
public interface ContaFilialRepository extends ContaRepository<ContaFilial>{

}
