package br.lumi.server.enuns;

public enum EUnidadeMedida {


	UND("Unidade"),
	KG("Quilos"),
	L("Litros"),
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
