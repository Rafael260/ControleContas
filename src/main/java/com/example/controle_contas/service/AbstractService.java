package com.example.controle_contas.service;

import java.util.List;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.controle_contas.domain.AbstractEntity;

@RequestMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
public class AbstractService<T extends AbstractEntity> {

	@Autowired
	protected JpaRepository<T, Long> repositorioGenerico;

	public AbstractService() {
		// TODO Auto-generated constructor stub
	}
	
	@GetMapping
	public List<T> findAll(){
		return this.repositorioGenerico.findAll();
	}
	
	@GetMapping("/{id}")
	public T findById(@PathParam("id") Long id) {
		return this.repositorioGenerico.getOne(id);
	}

	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public T insert(@RequestBody T entityObject) {
		if (entityObject != null) {
			entityObject.setId(null);
			return this.repositorioGenerico.save(entityObject);
		} else {
			return null;
		}
	}

	@PutMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public T update(@RequestBody T entityObject) {
		if (entityObject == null || entityObject.getId() == null) {
			return null;
		} else {
			return this.repositorioGenerico.save(entityObject);
		}
	}
	
	@DeleteMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	public void delete(@RequestBody T entityObject) {
		if(entityObject != null) {
			this.repositorioGenerico.delete(entityObject);
		}
	}
}
