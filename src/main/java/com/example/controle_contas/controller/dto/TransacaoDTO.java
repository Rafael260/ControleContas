package com.example.controle_contas.controller.dto;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransacaoDTO {

	@NotNull
	private Long idConta;
	
	@NotNull
	private Double valor;
}
