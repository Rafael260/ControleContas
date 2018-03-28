package com.example.controle_contas.repository;

import org.springframework.data.repository.CrudRepository;

import com.example.controle_contas.domain.AbstractEntity;

public interface AbstractRepository<E extends AbstractEntity> extends CrudRepository<E, Long> {

}
