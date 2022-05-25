package br.unitins.nicks.controller.listing;

import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.model.Produto;
import br.unitins.nicks.repository.ProdutoRepository;

@Named
@ViewScoped
public class ProdutoListing extends Listing<Produto> {

	private static final long serialVersionUID = -4010944566429542698L;
	private String filtro;
	
	public ProdutoListing() {
		super("produtolisting", new ProdutoRepository());
	}
	
	@Override
	public void pesquisar() {
		ProdutoRepository repo = new ProdutoRepository();
		try {
			setList(repo.findByDescricao(filtro));
		} catch (RepositoryException e) {
			setList(new ArrayList<Produto>());
		}
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}


}
