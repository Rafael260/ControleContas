package com.example.controle_contas.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import com.example.controle_contas.domain.AbstractEntity;
import com.example.controle_contas.repository.AbstractRepository;

public abstract class AbstractService<E extends AbstractEntity> {

	protected AbstractRepository<E> repository;

	public AbstractService(AbstractRepository<E> repository) {
		this.repository = repository;
	}

	public List<E> findAll() {
		Iterable<E> all = this.repository.findAll();
		List<E> allInList = new ArrayList<>();
		all.forEach(allInList::add);
		return allInList;
	}

	public E findById(Long id) {
		Optional<E> elementOptional = this.repository.findById(id);
		try {
			return elementOptional.get();
		}catch(NoSuchElementException e) {
			return null;
		}
	}

	public E insert(E entityObject) {
		if (entityObject != null) {
			onBeforeInsert(entityObject);
			entityObject.setId(null);
			entityObject = this.repository.save(entityObject);
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
			entityObject = this.repository.save(entityObject);
			onAfterUpdate(entityObject);
			return entityObject;
		}
	}

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
