package br.unitins.nicks.model;

import java.util.ArrayList;
import java.util.List;

public enum TipoUsuario {
	CLIENTE(1, "Cliente"),
	ADMINISTRADOR(2, "Administrador");
	
	private Integer id;
	private String label;
	private List<String> paginasComPermissao;
	
	TipoUsuario(Integer id, String label) {
		this.id = id;
		this.label = label;
		paginasComPermissao = new ArrayList<String>();
		
		// acesso para todos os usuarios
		paginasComPermissao.add("/NicksBabys/template.com");
		paginasComPermissao.add("/NicksBabys/pages/img-produto.com");
		
		if (id.equals(1)) { // cliente
			paginasComPermissao.add("/NicksBabys/pages/endereco.com");
			paginasComPermissao.add("/NicksBabys/pages/enderecolisting.com");
		} else if (id.equals(2)) { // adm
			paginasComPermissao.add("/NicksBabys/pages/usuario.com");
			paginasComPermissao.add("/NicksBabys/pages/usuariolisting.com");
			paginasComPermissao.add("/NicksBabys/pages/consultaUsuario.com");
			paginasComPermissao.add("/NicksBabys/pages/endereco.com");
			paginasComPermissao.add("/NicksBabys/pages/enderecolisting.com");
			paginasComPermissao.add("/NicksBabys/pages/pessoafisica.com");
			paginasComPermissao.add("/NicksBabys/pages/pessoafisicalisting.com");
			paginasComPermissao.add("/NicksBabys/pages/produto.com");
			paginasComPermissao.add("/NicksBabys/pages/produtolisting.com");
			paginasComPermissao.add("/NicksBabys/pages/pessoajuridica.com");
			paginasComPermissao.add("/NicksBabys/pages/pessoajuridicalisting.com");
			paginasComPermissao.add("/NicksBabys/pages/entradaproduto.com");
		}
	}
	
	public List<String> getPaginasComPermissao() {
		return paginasComPermissao;
	}
	
	public static TipoUsuario valueOf(Integer id) {
		if (id == null)
			return null;
		for (TipoUsuario tipo : TipoUsuario.values()) {
			if (tipo.getId().equals(id))
				return tipo;
		}
		return null;
	}
	
	public String getLabel() {
		return label;
	}
	
	public Integer getId() {
		return id;
	}
}