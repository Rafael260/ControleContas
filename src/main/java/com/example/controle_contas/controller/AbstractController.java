package com.example.controle_contas.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import com.example.controle_contas.domain.AbstractEntity;
import com.example.controle_contas.service.AbstractService;

public abstract class AbstractController<S extends AbstractService<E>, E extends AbstractEntity> {

	@Autowired
	protected S service;
	
	public AbstractController() {
	}

	@RequestMapping(method = RequestMethod.GET, value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public List<E> findAll(){
		return this.service.findAll();
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<E> findById(@PathVariable("id") Long id) {
		System.out.println("Chamou o metodo de findById");
		E element = this.service.findById(id);
		if(element != null) {
			return new ResponseEntity<E>(element,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
	public E insert(@RequestBody E entityObject) {
//		onBeforeInsert(entityObject);
		entityObject = this.service.insert(entityObject);
//		onAfterInsert(entityObject);
		return entityObject;
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public E update(@RequestBody E entityObject) {
//		onBeforeUpdate(entityObject);
		entityObject = this.service.update(entityObject);
//		onAfterUpdate(entityObject);
		return entityObject;
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@RequestBody E entityObject) {
//		onBeforeDelete(entityObject);
		this.service.delete(entityObject);
//		onAfterDelete(entityObject);
	}
	
	
//	//Metodos de ciclo de vida, para serem sobrescritos caso necessite
//	public void onBeforeInsert(E entityToPersist) {
//		
//	}
//	
//	public void onAfterInsert(E entityPersisted) {
//		
//	}
//	
//	public void onBeforeUpdate(E entityToUpdate) {
//		
//	}
//	
//	public void onAfterUpdate(E entityUpdated) {
//		
//	}
//	
//	public void onBeforeDelete(E entityToDelete) {
//		
//	}
//	
//	public void onAfterDelete(E entityDeleted) {
//		
//	}
}
