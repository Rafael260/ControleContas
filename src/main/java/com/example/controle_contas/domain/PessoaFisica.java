package com.example.controle_contas.domain;

import java.time.LocalDate;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("pessoa_fisica")
@JsonTypeName("pessoa_fisica")
@NoArgsConstructor
public class PessoaFisica extends Pessoa {

	@JsonProperty("type")
	private final String type = "pessoa_fisica";
	
	@NotNull
	@Getter
	@Setter
	private String cpf;
	
	@NotNull
	@Getter
	@Setter
	private String nomeCompleto;
	
	@Getter
	@Setter
	private LocalDate dataNascimento;
	
	public PessoaFisica(String cpf, String nomeCompleto, LocalDate dataNascimento) {
		super();
		this.cpf = cpf;
		this.nomeCompleto = nomeCompleto;
		this.dataNascimento = dataNascimento;
	}
}
