package br.unitins.nicks.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.nicks.model.PessoaJuridica;
import br.unitins.nicks.repository.PessoaJuridicaRepository;

@FacesConverter(forClass = PessoaJuridica.class)
public class PessoaJuridicaConverter implements Converter<PessoaJuridica> {

	@Override
	public PessoaJuridica getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		PessoaJuridicaRepository repo = new PessoaJuridicaRepository();
		try {
			return repo.findById(Integer.valueOf(value.trim()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, PessoaJuridica pessoaJuridica) {
		if (pessoaJuridica == null || pessoaJuridica.getId() == null)
			return null;
		
		return pessoaJuridica.getId().toString();
	}

}
