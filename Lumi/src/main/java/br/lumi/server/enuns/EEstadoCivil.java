package br.lumi.server.enuns;

public enum EEstadoCivil {

	SOLTEIRO("Solteiro(a)"),
	CASADO("Casado(a)");

	private String estadoCivil;

	private EEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	/**
	 * @return the estado civil
	 */
	public String getEstadoCivil() {
		return estadoCivil;
	}

	@Override
	public String toString() {
		return getEstadoCivil();
	}
}
