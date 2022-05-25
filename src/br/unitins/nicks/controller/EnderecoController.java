package br.unitins.nicks.controller;

import java.io.Serializable;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.nicks.controller.listing.EnderecoListing;
import br.unitins.nicks.model.Endereco;

@Named
@ViewScoped
public class EnderecoController extends Controller<Endereco> implements Serializable {

	private static final long serialVersionUID = 8004269176157113667L;

	@Override
	public Endereco getEntity() {
		if (entity == null) {
			entity = new Endereco();
		}
		return entity;
	}

	public void abrirEnderecoListing() {
		EnderecoListing listing = new EnderecoListing();
		listing.open();
	}

	public void obterEnderecoListing(SelectEvent<Endereco> event) {
		setEntity(event.getObject());
	}
}