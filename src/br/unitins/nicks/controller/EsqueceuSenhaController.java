package br.unitins.nicks.controller;

import java.io.Serializable;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.Random;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.nicks.application.Email;
import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.application.Util;
import br.unitins.nicks.model.RecuperarSenha;
import br.unitins.nicks.model.Usuario;
import br.unitins.nicks.repository.RecuperarSenhaRepository;
import br.unitins.nicks.repository.UsuarioRepository;

@Named
@ViewScoped
public class EsqueceuSenhaController implements Serializable {

	private static final long serialVersionUID = 1L;
	private String email;
	private String senha;
	private String codigo;
	private Usuario usuario;

	public void enviarEmail() {

		UsuarioRepository repo = new UsuarioRepository();
		Usuario usuario = null;
		try {
			usuario = repo.findByEmail(email);
		} catch (RepositoryException e) {
			Util.addErrorMessage("Email não encontrado.");
			return;
		}
		// gerando codigo aleatorio
		Random r = new Random();
		DecimalFormat format = new DecimalFormat("T-000000");
		String codigo = format.format(r.nextInt(1000000));

		RecuperarSenha entity = new RecuperarSenha();
		entity.setCodigo(codigo);
		entity.setUsuario(usuario);
		entity.setUtilizado(false);
		// definindo 24 horas de tempo limite
		entity.setDataLimite(LocalDateTime.now().plusDays(1));

		RecuperarSenhaRepository repoRecuperar = new RecuperarSenhaRepository();
		try {
			repoRecuperar.save(entity);
			Email email = new Email(usuario.getLogin(), "Esqueceu a Senha",
					codigo + " é seu código de recuperação de senha.");
			email.enviar();
			Util.addInfoMessage("O código foi enviado para o seu email.");
		} catch (RepositoryException e) {
			e.printStackTrace();
			Util.addErrorMessage("Falha ao enviar o código.");
		}

	}

	private boolean validarCodigo() {
		try {
			return !RecuperarSenhaRepository.buscarCodigo(getCodigo()).isEmpty();
		} catch (RepositoryException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

	public void mudarSenha() {
		if (validarCodigo()) {
			usuario.setSenha(getSenha());

		}
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}