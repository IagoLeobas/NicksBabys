package br.unitins.nicks.controller;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.nicks.application.Util;
import br.unitins.nicks.model.Usuario;
import br.unitins.nicks.repository.UsuarioRepository;

@Named
@ViewScoped
public class LoginController extends DefaultController {
	private static final long serialVersionUID = -2482810615603773001L;

	private Usuario usuario;

	public String entrar() {
		UsuarioRepository repo = new UsuarioRepository();
		Usuario usuarioValidado = repo.getUsuario(getUsuario().getLogin(), Util.hash(getUsuario().getSenha()));

		if (usuarioValidado == null) {
			Util.addErrorMessage("Usuário ou Senha inválido.");
			return null;
		}
		// setando o usuario na sessao
		setUsuarioLogado(usuarioValidado);

		return "template.xhtml?faces-redirect=true";
	}

	public Usuario getUsuario() {
		if (usuario == null)
			usuario = new Usuario();
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

}