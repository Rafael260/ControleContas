package com.example.controle_contas.service;

import java.util.List;
import com.example.controle_contas.domain.AbstractEntity;
import com.example.controle_contas.repository.AbstractRepository;

public abstract class AbstractService<E extends AbstractEntity> {

	protected AbstractRepository<E> repositorioGenerico;

	public AbstractService(AbstractRepository<E> repositorio) {
		this.repositorioGenerico = repositorio;
	}

	public List<E> findAll() {
		return this.repositorioGenerico.findAll();
	}

	public E findById(Long id) {
		return this.repositorioGenerico.getOne(id);
	}

	public E insert(E entityObject) {
		if (entityObject != null) {
			onBeforeInsert(entityObject);
			entityObject.setId(null);
			entityObject = this.repositorioGenerico.save(entityObject);
			onAfterInsert(entityObject);
			return entityObject;
		} else {
			return null;
		}
	}

	public E update(E entityObject) {
		if (entityObject == null || entityObject.getId() == null) {
			return null;
		} else {
			onBeforeUpdate(entityObject);
			entityObject = this.repositorioGenerico.save(entityObject);
			onAfterUpdate(entityObject);
			return entityObject;
		}
	}

	public void delete(E entityObject) {
		if (entityObject != null) {
			onBeforeDelete(entityObject);
			this.repositorioGenerico.delete(entityObject);
			onAfterDelete(entityObject);
		}
	}

	// Metodos de ciclo de vida, para serem sobrescritos caso necessite
	public void onBeforeInsert(E entityToPersist) {

	}

	public void onAfterInsert(E entityPersisted) {

	}

	public void onBeforeUpdate(E entityToUpdate) {

	}

	public void onAfterUpdate(E entityUpdated) {

	}

	public void onBeforeDelete(E entityToDelete) {

	}

	public void onAfterDelete(E entityDeleted) {

	}
}
