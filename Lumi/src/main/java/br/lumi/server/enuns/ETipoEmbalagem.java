package br.lumi.server.enuns;

public enum ETipoEmbalagem {

	POTE("Pote"),
	UNIDADE("Unidade"),
	BISNAGA("Bisnaga"),
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
