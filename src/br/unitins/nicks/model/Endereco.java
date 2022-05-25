package br.unitins.nicks.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

@Entity
public class Endereco extends DefaultEntity {

	@NotEmpty(message = "O cep deve ser preenchido.")
	private String cep;
	@NotEmpty(message = "A cidade deve ser preenchida.")
	private String cidade;
	@NotEmpty(message = "A rua deve ser preenchida.")
	private String rua;
	@NotEmpty(message = "O bairro deve ser preenchido.")
	private String bairro;
	@NotEmpty(message = "O número deve ser preenchido.")
	private Integer numero;
	private String complemento;

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}
}
