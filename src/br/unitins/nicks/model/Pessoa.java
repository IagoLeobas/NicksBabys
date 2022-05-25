package br.unitins.nicks.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public abstract class Pessoa extends DefaultEntity {

	@NotEmpty(message = "Campo nome não pode estar vazio!")
	private String nome;

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

}