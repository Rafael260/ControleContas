package com.example.controle_contas.service;

import java.util.List;
import com.example.controle_contas.domain.AbstractEntity;
import com.example.controle_contas.repository.AbstractRepository;

public abstract class AbstractService<E extends AbstractEntity> {

	protected AbstractRepository<E> repositorioGenerico;

	public AbstractService(AbstractRepository<E> repositorio) {
		this.repositorioGenerico = repositorio;
	}
	
	public List<E> findAll(){
		return this.repositorioGenerico.findAll();
	}
	
	public E findById(Long id) {
		return this.repositorioGenerico.getOne(id);
	}

	public E insert(E entityObject) {
		if (entityObject != null) {
			entityObject.setId(null);
			return this.repositorioGenerico.save(entityObject);
		} else {
			return null;
		}
	}

	public E update(E entityObject) {
		if (entityObject == null || entityObject.getId() == null) {
			return null;
		} else {
			return this.repositorioGenerico.save(entityObject);
		}
	}
	
	public void delete(E entityObject) {
		if(entityObject != null) {
			this.repositorioGenerico.delete(entityObject);
		}
	}
}
