package br.unitins.nicks.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.application.Util;
import br.unitins.nicks.model.Categoria;
import br.unitins.nicks.model.Produto;
import br.unitins.nicks.repository.ProdutoRepository;

@Named
@ViewScoped
public class RelatorioProdutoController implements Serializable {

	private static final long serialVersionUID = 2404198086475822591L;

	private Categoria categoria;
	private List<Produto> listaProduto;
	
	public void pesquisar() {
		ProdutoRepository repo = new ProdutoRepository();
		try {
			if (categoria == null)
				setListaProduto(repo.getAll(Produto.class));
			else
				setListaProduto(repo.findByCategoria(categoria));
		} catch (RepositoryException e) {
			Util.addErrorMessage("Problema ao consultar no banco de dados.");
			e.printStackTrace();
		}
	}
	
	public void limpar() {
		categoria = null;
		listaProduto = new ArrayList<Produto>();
	}
	
	public void gerarRelatorio() {
		if (categoria == null)
			Util.redirect("/NicksBabys/produtoreportservlet");
		else 
			Util.redirect("/NicksBabys/produtoreportservlet?categoria="+(categoria.getId() - 1));
	}
	
	public Categoria[] getListaCategoria() {
		return Categoria.values();
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public List<Produto> getListaProduto() {
		return listaProduto;
	}

	public void setListaProduto(List<Produto> listaProduto) {
		this.listaProduto = listaProduto;
	}
}