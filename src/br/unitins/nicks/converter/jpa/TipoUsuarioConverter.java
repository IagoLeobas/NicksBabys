package br.unitins.nicks.converter.jpa;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

import br.unitins.nicks.model.TipoUsuario;

@Converter(autoApply = true)
public class TipoUsuarioConverter implements AttributeConverter<TipoUsuario, Integer> {

	@Override
	public Integer convertToDatabaseColumn(TipoUsuario tipo) {
		return tipo == null ? null : tipo.getId();
	}

	@Override
	public TipoUsuario convertToEntityAttribute(Integer id) {
		return TipoUsuario.valueOf(id);
	}

}