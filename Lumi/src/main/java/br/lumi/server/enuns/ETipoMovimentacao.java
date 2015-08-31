package br.lumi.server.enuns;

public enum ETipoMovimentacao {

	ENTRADA("Entrada"),
	SAIDA("Saida");

	private String tipo;

	private ETipoMovimentacao(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return the tipo movimenta��o
	 */
	public String getTipo() {
		return tipo;
	}

	@Override
	public String toString() {
		return getTipo();
	}
}
