package br.lumi.server.enuns;

public enum EUf {

	AC("AC"),
	AL("AL"),
	AP("AP"),
	AM("AM"),
	BA("BA"),
	CE("CE"),
	DF("DF"),
	ES("ES"),
	GO("GO"),
	MA("MA"),
	MT("MT"),
	MS("MS"),
	MG("MG"),
	PA("PA"),
	PB("PB"),
	PR("PR"),
	PE("PE"),
	PI("PI"),
	RJ("RJ"),
	RN("RN"),
	RS("RS"),
	RO("RO"),
	RR("RR"),
	SC("SC"),
	SP("SP"),
	SE("SE"),
	TO("TO"),
	EX("EX");

	private String unidadeFederativa;

	private EUf(String unidadeFederativa) {
		this.unidadeFederativa = unidadeFederativa;
	}

	/**
	 * @return the tipo
	 */
	public String getUnidadeFederativa() {
		return unidadeFederativa;
	}

	@Override
	public String toString() {
		return getUnidadeFederativa();
	}
	
}
