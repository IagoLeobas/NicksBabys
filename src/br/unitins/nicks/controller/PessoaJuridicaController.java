package br.unitins.nicks.controller;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.nicks.controller.listing.PessoaJuridicaListing;
import br.unitins.nicks.model.PessoaJuridica;

@Named
@ViewScoped
public class PessoaJuridicaController extends Controller<PessoaJuridica> implements Serializable {

	
	private static final long serialVersionUID = -6715528440685894986L;

	@Override
	public PessoaJuridica getEntity() {
		if (entity == null)
			entity = new PessoaJuridica();
		return entity;
	}
	
	public void abrirPessoaJuridicaListing() {
		PessoaJuridicaListing listing = new PessoaJuridicaListing();
		listing.open();
	}
	
	public void obterPessoaJuridicaListing(SelectEvent<PessoaJuridica> event) {
		setEntity(event.getObject());
	}

	
}
