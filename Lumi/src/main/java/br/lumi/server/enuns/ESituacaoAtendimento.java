package br.lumi.server.enuns;

public enum ESituacaoAtendimento {

	MARCADO("Marcado"),
	CONFIRMADO("Confirmado"),
	REALIZADO("Realizado"),
	NAOREALIZADO("N�o Realizado"),
	CANCELADO("Cancelado"),
	;

	private String situacao;

	private ESituacaoAtendimento(String situacao) {
		this.situacao = situacao;
	}

	/**
	 * @return the situacao
	 */
	public String getSituacao() {
		return situacao;
	}

	@Override
	public String toString() {
		return getSituacao();
	}
	

}
