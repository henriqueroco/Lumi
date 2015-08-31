package br.lumi.server.enuns;

public enum ESexo {

	MASCULINO("Masculino"),
	FEMININO("Feminino");

	private String sexo;

	private ESexo(String sexo) {
		this.sexo = sexo;
	}

	/**
	 * @return the sexo
	 */
	public String getSexo() {
		return sexo;
	}

	@Override
	public String toString() {
		return getSexo();
	}
	
}
