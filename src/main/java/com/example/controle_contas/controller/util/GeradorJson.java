package com.example.controle_contas.controller.util;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class GeradorJson {

	private ObjectMapper mapper;
	
	public GeradorJson() {
		mapper = new ObjectMapper();
		mapper.enableDefaultTyping();
	}
	
	public String gerarJson(Object object) throws JsonProcessingException {
		return mapper.writeValueAsString(object);
	}
	
	public Object gerarObjeto(String json, Class<?> type) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(json, type);
	}

}
