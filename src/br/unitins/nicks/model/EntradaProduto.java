package br.unitins.nicks.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class EntradaProduto extends DefaultEntity {

	@ManyToOne
	@JoinColumn(name = "id_empresa")
	@NotNull(message = "Campo empresa não pode estar vazio!")
	private PessoaJuridica empresa;
	@ManyToOne
	@JoinColumn(name = "id_estoquista")
	@NotNull(message = "Campo estoquista não pode estar vazio!")
	private Usuario estoquista;
	@ManyToOne
	@JoinColumn(name = "id_produto")
	private Produto produto;
	private Integer qtdEntrante;

	public PessoaJuridica getEmpresa() {
		return empresa;
	}

	public void setEmpresa(PessoaJuridica empresa) {
		this.empresa = empresa;
	}

	public Usuario getEstoquista() {
		return estoquista;
	}

	public void setEstoquista(Usuario estoquista) {
		this.estoquista = estoquista;
	}

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getQtdEntrante() {
		return qtdEntrante;
	}

	public void setQtdEntrante(Integer qtdEntrante) {
		this.qtdEntrante = qtdEntrante;
	}
}
