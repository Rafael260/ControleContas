package com.example.controle_contas.domain;

import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo_pessoa")
public abstract class Pessoa extends AbstractEntity{

	public Pessoa() {
		
	}
}
