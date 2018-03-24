package com.example.controle_contas.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.controle_contas.controller.util.GeradorJson;
import com.example.controle_contas.domain.AbstractEntity;
import com.example.controle_contas.service.AbstractService;
import com.fasterxml.jackson.core.JsonProcessingException;

public abstract class AbstractController<S extends AbstractService<E>, E extends AbstractEntity> {

	@Autowired
	protected S service;
	
	private GeradorJson geradorJson;
	
	public AbstractController() {
		geradorJson = new GeradorJson();
	}

	@RequestMapping(method = RequestMethod.GET, value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public String findAll() throws JsonProcessingException{
		return geradorJson.gerarJson(this.service.findAll());
	}
	
	@RequestMapping(method = RequestMethod.GET, value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> findById(@PathVariable("id") Long id) throws JsonProcessingException {
		System.out.println("Chamou o metodo de findById");
		E element = this.service.findById(id);
		if(element != null) {
			String json = geradorJson.gerarJson(element);
			return new ResponseEntity<String>(json,HttpStatus.OK);
		}
		else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}
	
	@RequestMapping(method = RequestMethod.POST, value = "/insert", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String insert(@RequestBody E entityObject) throws JsonProcessingException {
		entityObject = this.service.insert(entityObject);
		return geradorJson.gerarJson(entityObject);
	}
	
	@RequestMapping(method = RequestMethod.PUT, value = "/update", consumes = MediaType.APPLICATION_JSON_VALUE)
	public String update(@RequestBody E entityObject) throws JsonProcessingException {
		entityObject = this.service.update(entityObject);
		return geradorJson.gerarJson(entityObject);
	}
	
	@RequestMapping(method = RequestMethod.DELETE, value = "/delete", consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@RequestBody E entityObject) {
		this.service.delete(entityObject);
	}
	
}
