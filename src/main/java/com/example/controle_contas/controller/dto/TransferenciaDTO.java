package com.example.controle_contas.controller.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransferenciaDTO {

	@NotNull
	private Long idContaOrigem;
	
	@NotNull
	private Long idContaDestino;
	
	@NotNull
	private Double valor;
}
