package com.example.controle_contas.repository;

import org.springframework.transaction.annotation.Transactional;

import com.example.controle_contas.domain.ContaMatriz;

@Transactional
public interface ContaMatrizRepository extends ContaRepository<ContaMatriz> {

}
