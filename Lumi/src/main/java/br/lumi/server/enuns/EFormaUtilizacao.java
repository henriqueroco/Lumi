package br.lumi.server.enuns;

public enum EFormaUtilizacao {

	FIXO("Fixo"),
	SUGERIDO("Sugerido");

	private String formaUtilizacao;

	private EFormaUtilizacao(String formaUtilizacao) {
		this.formaUtilizacao = formaUtilizacao;
	}

	/**
	 * @return the sexo
	 */
	public String getFormaUtilizacao() {
		return formaUtilizacao;
	}

	@Override
	public String toString() {
		return getFormaUtilizacao();
	}
	
}
