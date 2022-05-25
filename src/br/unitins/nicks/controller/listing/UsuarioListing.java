package br.unitins.nicks.controller.listing;

import java.util.ArrayList;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.model.Usuario;
import br.unitins.nicks.repository.UsuarioRepository;

@Named
@ViewScoped
public class UsuarioListing extends Listing<Usuario> {

	private static final long serialVersionUID = -4010944566429542698L;
	private String filtro;
	
	public UsuarioListing() {
		super("usuariolisting", new UsuarioRepository());
	}
	
	@Override
	public void pesquisar() {
		UsuarioRepository repo = new UsuarioRepository();
		try {
			setList(repo.findByNome(filtro));
		} catch (RepositoryException e) {
			setList(new ArrayList<Usuario>());
		}
	}

	public String getFiltro() {
		return filtro;
	}

	public void setFiltro(String filtro) {
		this.filtro = filtro;
	}


}
