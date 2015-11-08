package br.lumi.server.enuns;

public enum ETipoEmbalagem {
	
	AMPOLA("Ampola"),
	BISNAGA("Bisnaga"),
	CAIXA("Caixa"),
	POTE("Pote"),
	UNIDADE("Unidade"),
	;

	private String tipo;

	private ETipoEmbalagem(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the tipo embalagem
	 */
	public String getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return getTipo();
	}
}
