package com.example.controle_contas.domain;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa extends AbstractEntity{

	public Pessoa() {
		
	}
}
