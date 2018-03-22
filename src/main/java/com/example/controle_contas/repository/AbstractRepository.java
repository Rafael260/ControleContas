package com.example.controle_contas.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.controle_contas.domain.AbstractEntity;

public interface AbstractRepository<E extends AbstractEntity> extends JpaRepository<E, Long> {

}
