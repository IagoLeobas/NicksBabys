package br.unitins.nicks.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.nicks.controller.listing.PessoaFisicaListing;
import br.unitins.nicks.model.PessoaFisica;

@Named
@ViewScoped
public class PessoaFisicaController extends Controller<PessoaFisica> implements Serializable {

	
	private static final long serialVersionUID = -6715528440685894986L;

	@Override
	public PessoaFisica getEntity() {
		if (entity == null)
			entity = new PessoaFisica();
		return entity;
	}
	
	public void abrirPessoaFisicaListing() {
		PessoaFisicaListing listing = new PessoaFisicaListing();
		listing.open();
	}
	
	public void obterPessoaFisicaListing(SelectEvent<PessoaFisica> event) {
		setEntity(event.getObject());
	}

	
}
