package br.unitins.nicks.controller.listing;

import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.model.PessoaFisica;
import br.unitins.nicks.repository.PessoaFisicaRepository;

@Named
@ViewScoped
public class PessoaFisicaListing extends Listing<PessoaFisica> {

	private static final long serialVersionUID = 3362605174318519710L;
	private String filtro;
	
	public PessoaFisicaListing() {
		super("pessoafisicalisting", new PessoaFisicaRepository());
	}
	
	@Override
	public void pesquisar() {
		PessoaFisicaRepository repo = new PessoaFisicaRepository();
		try {
			setList(repo.findByNome(filtro));
		} catch (RepositoryException e) {
			setList(new ArrayList<PessoaFisica>());
		}
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}


}