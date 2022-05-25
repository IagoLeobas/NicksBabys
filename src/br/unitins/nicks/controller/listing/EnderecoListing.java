package br.unitins.nicks.controller.listing;

import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.model.Endereco;
import br.unitins.nicks.repository.EnderecoRepository;

@Named
@ViewScoped
public class EnderecoListing extends Listing<Endereco> {

	private static final long serialVersionUID = -4010944566429542698L;
	private String filtro;
	
	public EnderecoListing() {
		super("enderecolisting", new EnderecoRepository());
	}
	
	@Override
	public void pesquisar() {
		EnderecoRepository repo = new EnderecoRepository();
		try {
			setList(repo.findByCep(filtro));
		} catch (RepositoryException e) {
			setList(new ArrayList<Endereco>());
		}
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}


}
