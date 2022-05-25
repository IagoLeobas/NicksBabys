package br.unitins.nicks.model;

import javax.persistence.Entity;
import javax.validation.constraints.Pattern;

@Entity
public class PessoaFisica extends Pessoa {
	@Pattern(regexp = "[0-9]{3}[.][0-9]{3}[.][0-9]{3}[-][0-9]{2}", message = "O cpf deve seguir o seguinte padrão: ex.: 999.999.999-99")
	private String cpf;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

}