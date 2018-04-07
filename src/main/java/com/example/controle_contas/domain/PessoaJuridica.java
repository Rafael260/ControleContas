package com.example.controle_contas.domain;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@DiscriminatorValue("pessoa_juridica")
@JsonTypeName("pessoa_juridica")
@NoArgsConstructor
public class PessoaJuridica extends Pessoa {

	@JsonProperty("type")
	private final String type = "pessoa_juridica";
	
	@NotNull
	@Getter
	@Setter
	private String cnpj;
	
	@NotNull
	@Getter
	@Setter
	private String razaoSocial;
	
	@NotNull
	@Getter
	@Setter
	private String nomeFantasia;
	
	public PessoaJuridica(String cnpj, String razaoSocial, String nomeFantasia) {
		super();
		this.cnpj = cnpj;
		this.razaoSocial = razaoSocial;
		this.nomeFantasia = nomeFantasia;
	}
}
