package br.unitins.nicks.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.primefaces.event.FileUploadEvent;
import org.primefaces.event.SelectEvent;
import org.primefaces.model.file.UploadedFile;

import br.unitins.nicks.application.RepositoryException;
import br.unitins.nicks.application.Util;
import br.unitins.nicks.controller.listing.ProdutoListing;
import br.unitins.nicks.model.Categoria;
import br.unitins.nicks.model.Produto;
import br.unitins.nicks.repository.ProdutoRepository;

@Named
@ViewScoped
public class ProdutoController extends Controller<Produto> implements Serializable {

	private static final long serialVersionUID = 8004269176157113667L;

	private InputStream fotoInputStream = null;
	
	public Categoria[] getListaCategoria() {
		return Categoria.values();
	}

	public void upload(FileUploadEvent event) {
		UploadedFile uploadFile = event.getFile();
		System.out.println("nome arquivo: " + uploadFile.getFileName());
		System.out.println("tipo: " + uploadFile.getContentType());
		System.out.println("tamanho: " + uploadFile.getSize());

		if (uploadFile.getContentType().equals("image/png")) {
			try {
				fotoInputStream = uploadFile.getInputStream();
				System.out.println("inputStream: " + uploadFile.getInputStream().toString());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Util.addInfoMessage("Upload realizado com sucesso.");
		} else {
			Util.addErrorMessage("O tipo da image deve ser png.");
		}

	}

	@Override
	public void salvar() {
		try {
			salvarPrincipal();

			if (getFotoInputStream() != null) {
				// salvando a imagem
				if (!Util.saveImageProduto(fotoInputStream, "png", getEntity().getId())) {
					Util.addErrorMessage("Erro ao salvar. Não foi possível salvar a imagem do produto.");
					return;
				}
			}
			limpar();
		} catch (RepositoryException e) {

			Util.addErrorMessage("Problema ao salvar o usuário");
		}

	}

	@Override
	public Produto getEntity() {
		if (entity == null) {
			entity = new Produto();
		}
		return entity;
	}

	public void abrirProdutoListing() {
		ProdutoListing listing = new ProdutoListing();
		listing.open();
	}

	public void obterProdutoListing(SelectEvent<Produto> event) {
		setEntity(event.getObject());
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

	public InputStream getFotoInputStream() {
		return fotoInputStream;
	}

	public void setFotoInputStream(InputStream fotoInputStream) {
		this.fotoInputStream = fotoInputStream;
	}
}