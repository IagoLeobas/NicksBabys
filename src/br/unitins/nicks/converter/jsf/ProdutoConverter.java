package br.unitins.nicks.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.nicks.model.Produto;
import br.unitins.nicks.repository.ProdutoRepository;

@FacesConverter(forClass = Produto.class)
public class ProdutoConverter implements Converter<Produto> {

	@Override
	public Produto getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		ProdutoRepository repo = new ProdutoRepository();
		try {
			return repo.findById(Integer.valueOf(value.trim()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Produto produto) {
		if (produto == null || produto.getId() == null)
			return null;
		
		return produto.getId().toString();
	}

}
