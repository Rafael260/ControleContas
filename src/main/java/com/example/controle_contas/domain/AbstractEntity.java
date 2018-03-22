package com.example.controle_contas.domain;

import javax.persistence.MappedSuperclass;

import org.springframework.data.jpa.domain.AbstractPersistable;

@MappedSuperclass
public class AbstractEntity extends AbstractPersistable<Long> {

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
}
