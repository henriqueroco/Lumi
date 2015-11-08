package br.lumi.server.enuns;

public enum EUnidadeMedida {

	KG("Quilos"),
	G("Gramas"),
	MG("Miligramas"),
	UND("Unidade"),
	L("Litros"),
	ML("Mililitros"),
	;

	private String unidadeMedida;

	private EUnidadeMedida(String unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	/**
	 * @return the unidade medida
	 */
	public String getUnidadeMedida() {
		return unidadeMedida;
	}

	@Override
	public String toString() {
		return getUnidadeMedida();
	}
}
