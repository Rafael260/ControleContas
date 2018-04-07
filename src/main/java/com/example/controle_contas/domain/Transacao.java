package com.example.controle_contas.domain;

import java.time.LocalDateTime;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import org.springframework.data.annotation.CreatedDate;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonSubTypes.Type;
import lombok.Getter;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, include = JsonTypeInfo.As.PROPERTY, property = "type")
@JsonSubTypes({ @Type(value = Transferencia.class, name = "transferencia"), @Type(value = Carga.class, name = "carga"),
		@Type(value = Debito.class, name = "debito"), @Type(value = Aporte.class, name = "aporte") })
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "tipo_transacao")
public abstract class Transacao extends AbstractEntity implements Comparable<Transacao> {

	@NotNull
	@Getter
	@Setter
	protected Double valor;

	@ManyToOne
	@Getter
	@Setter
	protected Conta contaEnvolvida;

	@CreatedDate
	@Getter
	@Setter
	protected LocalDateTime data;

	@Getter
	@Setter
	protected boolean estornada;

	public Transacao() {
		estornada = false;
	}

	public Transacao(Conta contaEnvolvida, Double valor) {
		this.valor = valor;
		this.contaEnvolvida = contaEnvolvida;
		estornada = false;
	}

	@Override
	public int compareTo(Transacao o) {
		return this.data.compareTo(o.getData());
	}
}
