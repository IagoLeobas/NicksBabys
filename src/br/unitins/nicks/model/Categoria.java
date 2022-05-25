package br.unitins.nicks.model;

public enum Categoria {
	CAMISA(1, "Camisa"),
	CALCA(2, "Cal�a"),
	SHORT(3, "Short"),
	CAL�ADO(4, "Cal�ado"),
	BONE(5, "Bon�"),
	ACESSORIO(6, "Acess�rio");
	
	private Integer id;
	private String label;
	
	Categoria(Integer id, String label) {
		this.id = id;
		this.label = label;
	}
	
	
	public static Categoria valueOf(Integer id) {
		if (id == null)
			return null;
		for (Categoria categoria : Categoria.values()) {
			if (categoria.getId().equals(id))
				return categoria;
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