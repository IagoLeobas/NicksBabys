package br.unitins.nicks.model;

import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Entity
public class Produto extends DefaultEntity {

	@NotEmpty(message = "Campo descrição não pode estar vazio!")
	private String descricao;
	@NotEmpty(message = "Campo marca não pode estar vazio!")
	private String marca;
	@NotEmpty(message = "Campo cor não pode estar vazio!")
	private String cor;
	@NotEmpty(message = "Campo idade não pode estar vazio!")
	private String idade;
	private Categoria categoria;
	@NotNull(message = "Campo preço não pode estar vazio!")
	private Double preco;
	@NotNull(message = "Campo estoque não pode estar vazio!")
	private Integer qtdEstoque;

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getIdade() {
		return idade;
	}

	public void setIdade(String idade) {
		this.idade = idade;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQtdEstoque() {
		return qtdEstoque;
	}

	public Integer getQtdEstoque(Integer qtdEntrante) {
		return qtdEstoque + qtdEntrante;
	}

	public void setQtdEstoque(Integer qtdEstoque) {
		this.qtdEstoque = qtdEstoque;
	}
}
