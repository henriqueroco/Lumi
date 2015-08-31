package br.lumi.server.enuns;

public enum ESituacao {

	ATIVO("Ativo(a)"),
	INATIVO("Inativo(a)");

	private String situacao;

	private ESituacao(String situacao) {
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
