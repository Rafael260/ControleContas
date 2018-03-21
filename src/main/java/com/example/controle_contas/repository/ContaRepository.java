package com.example.controle_contas.repository;


import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import com.example.controle_contas.domain.Conta;

@NoRepositoryBean
public interface ContaRepository<T extends Conta> extends CrudRepository<T, Long>{

	public T findByNumero(String numero);
}
