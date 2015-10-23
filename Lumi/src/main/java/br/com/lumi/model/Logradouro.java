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
import br.com.lumi.model.Bairro;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = Logradouro.TABELA)
@XmlRootElement
public class Logradouro implements Serializable {

	static final String TABELA = "logradouro";
	private static final String ATRIBUTO_ID = "id";
	private static final String ATIBUTO_VERSION = "version";
	private static final String ATRIBUTO_DESCRICAO_LOGRADOURO = "descricao_logradouro";
	private static final String ATRIBUTO_CEP = "cep";
	private static final String ATRIBUTO_NUMERO = "numero";
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = ATRIBUTO_ID, updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = ATIBUTO_VERSION)
	private int version;

	@Column(length = 100, name = ATRIBUTO_DESCRICAO_LOGRADOURO, nullable = false)
	private String descricaoLogradouro;

	@Column(length = 10, name = ATRIBUTO_CEP)
	private String cep;

	@Column(name = ATRIBUTO_NUMERO)
	private Integer numero;

	@ManyToOne
	private Bairro bairro;

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
		if (!(obj instanceof Logradouro)) {
			return false;
		}
		Logradouro other = (Logradouro) obj;
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

	public String getDescricaoLogradouro() {
		return descricaoLogradouro;
	}

	public void setDescricaoLogradouro(String descricaoLogradouro) {
		this.descricaoLogradouro = descricaoLogradouro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (descricaoLogradouro != null
				&& !descricaoLogradouro.trim().isEmpty())
			result += "descricaoLogradouro: " + descricaoLogradouro;
		if (cep != null && !cep.trim().isEmpty())
			result += ", cep: " + cep;
		if (numero != null)
			result += ", numero: " + numero;
		return result;
	}

	public Bairro getBairro() {
		return this.bairro;
	}

	public void setBairro(final Bairro bairro) {
		this.bairro = bairro;
	}
}