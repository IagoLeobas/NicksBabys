package br.unitins.nicks.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.application.Util;
import br.unitins.nicks.model.EntradaProduto;
import br.unitins.nicks.model.PessoaJuridica;
import br.unitins.nicks.model.Produto;
import br.unitins.nicks.model.Usuario;
import br.unitins.nicks.repository.PessoaJuridicaRepository;
import br.unitins.nicks.repository.ProdutoRepository;
import br.unitins.nicks.repository.UsuarioRepository;

@Named
@ViewScoped
public class EntradaProdutoController extends Controller<EntradaProduto> implements Serializable {

	private static final long serialVersionUID = -6715528440685894986L;

	@Override
	public EntradaProduto getEntity() {
		if (entity == null)
			entity = new EntradaProduto();
		return entity;
	}

/*	@Override
	public void salvar() {
		try {
			salvarPrincipal();

			entity.getProduto().setQtdEstoque(entity.getProduto().getQtdEstoque() + entity.getQtdEntrante());

			limpar();
		} catch (RepositoryException e) {

			Util.addErrorMessage("Problema ao salvar o Entrada de Produto");
		}

	}*/

	public List<PessoaJuridica> completeEmpresa(String query) {
		PessoaJuridicaRepository repo = new PessoaJuridicaRepository();
		try {
			return repo.findByNome(query);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<PessoaJuridica>();
	}

	public List<Produto> completeProduto(String query) {
		ProdutoRepository repo = new ProdutoRepository();
		try {
			return repo.findByDescricao(query);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Produto>();
	}

	public List<Usuario> completeUsuario(String query) {
		UsuarioRepository repo = new UsuarioRepository();
		try {
			return repo.findByLogin(query);
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return new ArrayList<Usuario>();
	}
}
