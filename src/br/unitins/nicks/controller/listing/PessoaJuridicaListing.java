package br.unitins.nicks.controller.listing;

import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.model.PessoaJuridica;
import br.unitins.nicks.repository.PessoaJuridicaRepository;

@Named
@ViewScoped
public class PessoaJuridicaListing extends Listing<PessoaJuridica> {

	private static final long serialVersionUID = 3362605174318519710L;
	private String filtro;
	
	public PessoaJuridicaListing() {
		super("pessoajuridicalisting", new PessoaJuridicaRepository());
	}
	
	@Override
	public void pesquisar() {
		PessoaJuridicaRepository repo = new PessoaJuridicaRepository();
		try {
			setList(repo.findByNome(filtro));
		} catch (RepositoryException e) {
			setList(new ArrayList<PessoaJuridica>());
		}
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}


}