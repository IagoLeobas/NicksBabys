package br.unitins.nicks.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.SelectEvent;

import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.application.Util;
import br.unitins.nicks.controller.listing.PessoaFisicaListing;
import br.unitins.nicks.controller.listing.UsuarioListing;
import br.unitins.nicks.model.Endereco;
import br.unitins.nicks.model.PessoaFisica;
import br.unitins.nicks.model.TipoUsuario;
import br.unitins.nicks.model.Usuario;
import br.unitins.nicks.repository.EnderecoRepository;
import br.unitins.nicks.repository.UsuarioRepository;

@Named
@ViewScoped
public class UsuarioController extends Controller<Usuario> implements Serializable {

	private static final long serialVersionUID = 8004269176157113667L;
	private String confirmarSenha;

	private boolean verificaSenha() {
		if (getEntity().getSenha().equals(getConfirmarSenha())) {
			return true;
		}
		Util.addErrorMessage("As senhas estão diferentes.");
		return false;
	}

	public TipoUsuario[] getListaTipoUsuario() {
		return TipoUsuario.values();
	}

	@Override
	public Usuario getEntity() {
		if (entity == null) {
			entity = new Usuario();
		}
		if (entity.getPessoaFisica() == null) {
			entity.setPessoaFisica(new PessoaFisica());
		}
		return entity;
	}

	@Override
	public void salvar() {
		if (!verificaSenha()) {
			return;
		}
		getEntity().setSenha(Util.hash(getEntity().getSenha()));

		try {
			salvarPrincipal();
			limpar();
		} catch (RepositoryException e) {
			Util.addErrorMessage("Problema ao salvar o usuário");
		}
	}

	public void salvarCadastro() {
		if (!verificaSenha()) {
			return;
		}
		getEntity().setSenha(Util.hash(getEntity().getSenha()));
		getEntity().setTipoUsuario(TipoUsuario.CLIENTE);

		try {
			salvarPrincipal();
			limpar();
		} catch (RepositoryException e) {
			Util.addErrorMessage("Problema ao salvar o usuário");
		}

	}

	public void abrirUsuarioListing() {
		UsuarioListing listing = new UsuarioListing();
		listing.open();
	}

	public void obterUsuarioListing(SelectEvent<Usuario> event) {
		setEntity(event.getObject());
	}

	public void abrirPessoaFisicaListing() {
		PessoaFisicaListing listing = new PessoaFisicaListing();
		listing.open();
	}

	public void obterPessoaFisicaListing(SelectEvent<PessoaFisica> event) {
		getEntity().setPessoaFisica(event.getObject());
		UsuarioRepository repo = new UsuarioRepository();
		try {
			Usuario usu = repo.findByPessoaFisica(getEntity().getPessoaFisica());
			if (usu != null)
				setEntity(usu);
		} catch (RepositoryException e) {
			Util.addErrorMessage(e.getMessage());
		}
	}

	public List<Endereco> completeEndereco(String query) {
		EnderecoRepository repo = new EnderecoRepository();
		try {
			return repo.findByCep(query);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Endereco>();
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}
}
