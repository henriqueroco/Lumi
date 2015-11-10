package br.com.lumi.model;

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.Table;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Version;

import java.lang.Override;

import br.lumi.server.enuns.EUf;

import javax.persistence.Enumerated;

import br.lumi.server.enuns.EPais;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = Localidade.TABELA)
@XmlRootElement
public class Localidade implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final String TABELA = "localidade";
	private static final String ATRIBUTO_ID = "id";
	private static final String ATRIBUTO_VERSION = "version";
	private static final String ATRIBUTO_DESCRICAO_CIDADE = "descricao_cidade";
	private static final String ATRIBUTO_UNIDADE_FEDERATIVA = "unidade_federativa";
	private static final String ATIBUTO_PAIS = "pais";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = ATRIBUTO_ID, updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = ATRIBUTO_VERSION)
	private int version;

	@Column(length = 100, name = ATRIBUTO_DESCRICAO_CIDADE, nullable = false)
	private String nomeCidade;

	@Enumerated
	@Column(name = ATRIBUTO_UNIDADE_FEDERATIVA, nullable = false, length = 2)
	private EUf uf;

	@Enumerated
	@Column(name = ATIBUTO_PAIS, nullable = false, length = 2)
	private EPais pais;

	public Long getId() {
		return this.id;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public int getVersion() {
		return this.version;
	}

	public void setVersion(final int version) {
		this.version = version;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (!(obj instanceof Localidade)) {
			return false;
		}
		Localidade other = (Localidade) obj;
		if (id != null) {
			if (!id.equals(other.id)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	public String getNomeCidade() {
		return nomeCidade;
	}

	public void setNomeCidade(String nomeCidade) {
		this.nomeCidade = nomeCidade;
	}

	public EUf getUf() {
		return uf;
	}

	public void setUf(EUf uf) {
		this.uf = uf;
	}

	public EPais getPais() {
		return pais;
	}

	public void setPais(EPais pais) {
		this.pais = pais;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (nomeCidade != null && !nomeCidade.trim().isEmpty())
			result += "nomeCidade: " + nomeCidade;
		return result;
	}
}