package br.unitins.nicks.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.nicks.model.Endereco;
import br.unitins.nicks.repository.EnderecoRepository;

@FacesConverter(forClass = Endereco.class)
public class EnderecoConverter implements Converter<Endereco> {

	@Override
	public Endereco getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		EnderecoRepository repo = new EnderecoRepository();
		try {
			return repo.findById(Integer.valueOf(value.trim()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Endereco endereco) {
		if (endereco == null || endereco.getId() == null)
			return null;
		
		return endereco.getId().toString();
	}

}
