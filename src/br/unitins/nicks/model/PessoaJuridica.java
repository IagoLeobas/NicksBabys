package br.unitins.nicks.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class PessoaJuridica extends Pessoa {
	@NotEmpty(message = "Campo cnpj não pode estar vazio!")
	private String cnpj;

	public String getCnpj() {
		return cnpj;
	}

	public void setCnpj(String cnpj) {
		this.cnpj = cnpj;
	}

}
