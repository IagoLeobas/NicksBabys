package br.unitins.nicks.converter.jsf;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.unitins.nicks.model.Usuario;
import br.unitins.nicks.repository.UsuarioRepository;

@FacesConverter(forClass = Usuario.class)
public class UsuarioConverter implements Converter<Usuario> {

	@Override
	public Usuario getAsObject(FacesContext context, UIComponent component, String value) {
		if (value == null || value.isEmpty())
			return null;
		
		UsuarioRepository repo = new UsuarioRepository();
		try {
			return repo.findById(Integer.valueOf(value.trim()));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Usuario usuario) {
		if (usuario == null || usuario.getId() == null)
			return null;
		
		return usuario.getId().toString();
	}

}
