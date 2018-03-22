package com.example.controle_contas.controller;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.example.controle_contas.domain.AbstractEntity;
import com.example.controle_contas.service.AbstractService;

public abstract class AbstractController<S extends AbstractService<E>, E extends AbstractEntity> {

	@Autowired
	protected S service;
	
	public AbstractController() {
	}

	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<E> findAll(){
		return this.service.findAll();
	}
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public E findById(@PathParam("id") Long id) {
		return this.service.findById(id);
	}
	
	@PostMapping(value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
	public E insert(@RequestBody E entityObject) {
		onBeforeInsert(entityObject);
		entityObject = this.service.insert(entityObject);
		onAfterInsert(entityObject);
		return entityObject;
	}
	
	@PutMapping(value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public E update(@RequestBody E entityObject) {
		onBeforeUpdate(entityObject);
		entityObject = this.service.update(entityObject);
		onAfterUpdate(entityObject);
		return entityObject;
	}
	
	@DeleteMapping(value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@RequestBody E entityObject) {
		onBeforeDelete(entityObject);
		this.service.delete(entityObject);
		onAfterDelete(entityObject);
	}
	
	
	//Metodos de ciclo de vida, para serem sobrescritos caso necessite
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
