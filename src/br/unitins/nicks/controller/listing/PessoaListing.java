package br.unitins.nicks.controller.listing;

import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.model.Pessoa;
import br.unitins.nicks.repository.PessoaRepository;

@Named
@ViewScoped
public class PessoaListing extends Listing<Pessoa> {

	private static final long serialVersionUID = -7485258844382965206L;
	private String filtro;
	
	public PessoaListing() {
		super("pessoalisting", new PessoaRepository());
	}
	
	@Override
	public void pesquisar() {
		PessoaRepository repo = new PessoaRepository();
		try {
			setList(repo.findByNome(filtro));
		} catch (RepositoryException e) {
			setList(new ArrayList<Pessoa>());
		}
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}


}