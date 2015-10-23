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
import br.com.lumi.model.Localidade;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = Bairro.TABELA)
@XmlRootElement
public class Bairro implements Serializable {

	static final String TABELA = "bairro";
	private static final String ATRIBUTO_ID = "id";
	private static final String ATRIBUTO_VERSION = "version";
	private static final String ATRIBUTO_DESCRICAO_BAIRRO = "descricao_bairro";
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = ATRIBUTO_ID, updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = ATRIBUTO_VERSION)
	private int version;

	@Column(length = 100, name = ATRIBUTO_DESCRICAO_BAIRRO, nullable = false)
	private String descricaoBairro;

	@ManyToOne
	private Localidade localidade;

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
		if (!(obj instanceof Bairro)) {
			return false;
		}
		Bairro other = (Bairro) obj;
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

	public String getDescricaoBairro() {
		return descricaoBairro;
	}

	public void setDescricaoBairro(String descricaoBairro) {
		this.descricaoBairro = descricaoBairro;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (descricaoBairro != null && !descricaoBairro.trim().isEmpty())
			result += "descricaoBairro: " + descricaoBairro;
		return result;
	}

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(final Localidade localidade) {
		this.localidade = localidade;
	}
}