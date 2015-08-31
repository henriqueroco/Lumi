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

@Entity
@Table(name = Procedimento.TABELA)
public class Procedimento implements Serializable {

	private static final long serialVersionUID = 6444340500873055082L;

	static final String TABELA = "procedimento";
	private static final String ATRIBUTO_ID = "id";
	private static final String VERSAO = "version";
	private static final String ATRIBUTO_DESCRICAO = "descricao";

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = ATRIBUTO_ID, updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = VERSAO)
	private int version;

	@Column(name = ATRIBUTO_DESCRICAO, nullable = false)
	private String descricao;

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
		if (!(obj instanceof Procedimento)) {
			return false;
		}
		Procedimento other = (Procedimento) obj;
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

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (descricao != null && !descricao.trim().isEmpty())
			result += "descricao: " + descricao;
		return result;
	}
}