package br.lumi.server.enuns;

public enum ETipoUsuario {

	ESTETICISTA("Esteticista"),
	ATENDIMENTO("Atrendimento");

	private String tipoUsuario;

	private ETipoUsuario(String tipoUsuario) {
		this.tipoUsuario = tipoUsuario;
	}

	/**
	 * @return the tipo
	 */
	public String getTipoUsuario() {
		return tipoUsuario;
	}

	@Override
	public String toString() {
		return getTipoUsuario();
	}
	
}
