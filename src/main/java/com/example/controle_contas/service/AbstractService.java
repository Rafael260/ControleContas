package com.example.controle_contas.service;

import com.example.controle_contas.domain.AbstractEntity;
import com.example.controle_contas.exceptions.ResourceNotFoundException;
import com.example.controle_contas.repository.AbstractRepository;

public abstract class AbstractService<E extends AbstractEntity> {

	protected AbstractRepository<E> repository;

	public AbstractService(AbstractRepository<E> repository) {
		this.repository = repository;
	}

	public Iterable<E> findAll() {
		return this.repository.findAll();
	}

	public E findById(Long id) {
		return this.repository.findById(id).orElseThrow(() -> new ResourceNotFoundException());
	}

	public E insert(E entityObject) {
		onBeforeInsert(entityObject);
		entityObject.setId(null);
		entityObject = this.repository.save(entityObject);
		onAfterInsert(entityObject);
		return entityObject;
	}

	public E update(E entityObject) {
		onBeforeUpdate(entityObject);
		entityObject = this.repository.save(entityObject);
		onAfterUpdate(entityObject);
		return entityObject;
	}

	//TODO retornar algum feedback sobre a operação
	public void delete(E entityObject) {
		if (entityObject != null) {
			onBeforeDelete(entityObject);
			this.repository.delete(entityObject);
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
