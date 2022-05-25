package br.unitins.nicks.controller;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.application.Util;
import br.unitins.nicks.model.RecuperarSenha;
import br.unitins.nicks.model.Usuario;
import br.unitins.nicks.repository.RecuperaSenhaRepository;
import br.unitins.nicks.repository.UsuarioRepository;

@Named
@ViewScoped
public class RecuperarSenhaController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8016964725466808560L;
	private String codigo;
	private String senha;

	public void alterarSenha() {
		UsuarioRepository repoUsu = new UsuarioRepository();
		RecuperaSenhaRepository repo = new RecuperaSenhaRepository();

		RecuperarSenha obj = null;
		try {
			obj = repo.findByCodigo(getCodigo());
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		if (obj == null || obj.getDataLimite().isBefore(LocalDateTime.now()) || obj.getUtilizado() == true) {

			Util.addErrorMessage("Esse código é invalido ou expirou.");
			limpar();
		} else {
			Usuario usu = obj.getUsuario();
			usu.setSenha(getSenha());
			obj.setUtilizado(true);
			try {
				repoUsu.adicionar(usu);
				repo.adicionar(obj);
				Util.addInfoMessage("Senha alterada com sucesso.");
			} catch (RepositoryException e) {
				Util.addErrorMessage("Problemas ao alterar senha.");
				e.printStackTrace();
			}
			limpar();
		}

	}

	public void limpar() {
		setSenha(null);
		setCodigo(null);
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	

}