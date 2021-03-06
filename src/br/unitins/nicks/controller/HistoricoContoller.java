package br.unitins.nicks.controller;

import java.io.Serializable;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import br.unitins.nicks.application.Session;
import br.unitins.nicks.application.Util;
import br.unitins.nicks.dao.VendaDao;
import br.unitins.nicks.model.Usuario;
import br.unitins.nicks.model.Venda;


@Named
@ViewScoped
public class HistoricoContoller implements Serializable {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3811470406849996443L;
	private List<Venda> listavendas;
	private Usuario usuarioLogado;
	
	public void detalhes(Venda venda) {
		Flash flash = FacesContext.getCurrentInstance().getExternalContext().getFlash();
		flash.put("detalhesFlash", venda);
		
		Util.redirect("detalhesvenda.xhtml");
	}
	
	
	public List<Venda> getListavendas() {
		VendaDao dao = new VendaDao();
		listavendas = dao.obterTodos(getUsuarioLogado());
		return listavendas;
	}

	public void setListavendas(List<Venda> listavendas) {
		this.listavendas = listavendas;
	}

	public Usuario getUsuarioLogado() {
		if (usuarioLogado == null) {
			usuarioLogado = (Usuario) Session.getInstance().get("usuarioLogado");
		}
		return usuarioLogado;
	}

	public void setUsuarioLogado(Usuario usuarioLogado) {
		this.usuarioLogado = usuarioLogado;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}