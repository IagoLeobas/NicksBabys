package br.unitins.nicks.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.application.Session;
import br.unitins.nicks.application.Util;
import br.unitins.nicks.model.Categoria;
import br.unitins.nicks.model.ItemVenda;
import br.unitins.nicks.model.Produto;
import br.unitins.nicks.repository.ProdutoRepository;

@Named
@ViewScoped
public class VendaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1898794228698382029L;
	private int tipoFiltro;
	private String filtro;
	private Categoria categoria;

	private List<Produto> listaProduto;

	public Categoria[] getListaCategoria() {
		return Categoria.values();
	}

	public void pesquisar() {
		ProdutoRepository repo = new ProdutoRepository();
		try {
			setListaProduto(repo.findByDescricao(filtro));
		} catch (RepositoryException e) {
			setListaProduto(new ArrayList<Produto>());
		}
	}

	public void pesquisarCategoria() {
		ProdutoRepository repo = new ProdutoRepository();
		try {
			setListaProduto(repo.findByCategoria(categoria));
		} catch (RepositoryException e) {
			setListaProduto(new ArrayList<Produto>());
		}
	}

	public void addCarrinho(Produto produto) {

		@SuppressWarnings("unchecked")
		List<ItemVenda> carrinho = (List<ItemVenda>) Session.getInstance().get("carrinho");
		if (carrinho == null)
			carrinho = new ArrayList<ItemVenda>();

		ItemVenda iv = new ItemVenda();
		iv.setProduto(produto);
		iv.setQuantidade(1);
		iv.setValorUnitario((double) produto.getPreco());

		if (carrinho.contains(iv)) {
			int index = carrinho.indexOf(iv);
			int quantidade = carrinho.get(index).getQuantidade();
			carrinho.get(index).setQuantidade(++quantidade);

		} else {
			carrinho.add(iv);
		}

		Session.getInstance().set("carrinho", carrinho);

		Util.addInfoMessage("Item adicionado no carrinho.");

	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}

	public List<Produto> getListaProduto() {
		if (listaProduto == null) {
			listaProduto = new ArrayList<Produto>();
		}
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}

	public int getTipoFiltro() {
		return tipoFiltro;
	}

	public void setTipoFiltro(int tipoFiltro) {
		this.tipoFiltro = tipoFiltro;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}
}