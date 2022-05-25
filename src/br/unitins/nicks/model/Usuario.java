package br.unitins.nicks.model;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Entity
public class Usuario extends DefaultEntity {

	@OneToOne(cascade = {CascadeType.ALL})
	@JoinColumn(name = "id_pessoafisica", unique = true)
	private PessoaFisica pessoaFisica;
	@Email(message = "login deve ser um email válido.")
	@NotEmpty(message = "Campo login não pode estar vazio!")
	private String login;
	@NotEmpty(message = "Campo senha não pode estar vazio!")
	private String senha;
	@NotNull(message = "Campo tipo não pode estar vazio!")
	private TipoUsuario tipoUsuario;
	@ManyToOne
	@JoinColumn(name = "id_endereco")
	//@NotNull(message = "Campo endereço não pode estar vazio!")
	private Endereco endereco;

	public Endereco getEndereco() {
		return endereco;
	}

	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public TipoUsuario getTipoUsuario() {
		return tipoUsuario;
	}

	public void setTipoUsuario(TipoUsuario tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}
	
	public PessoaFisica getPessoaFisica() {
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica) {
		this.pessoaFisica = pessoaFisica;
	}

}
