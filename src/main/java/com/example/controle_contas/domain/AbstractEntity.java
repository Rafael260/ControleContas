package com.example.controle_contas.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

public class AbstractEntity extends AbstractPersistable<Long> {

	@Override
	public void setId(Long id) {
		super.setId(id);
	}
}
