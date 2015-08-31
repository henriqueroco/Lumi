package br.com.lumi.model;

import javax.persistence.Entity;

import java.io.Serializable;

import javax.persistence.JoinColumn;
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
import br.com.lumi.model.Bairro;

import java.util.Set;
import java.util.HashSet;

import javax.persistence.OneToMany;

@Entity
@Table(name = Localidade.TABELA)
public class Localidade implements Serializable {

	private static final long serialVersionUID = -8283116906037311354L;

	static final String TABELA = "localidade";
	private static final String ATRIBUTO_ID = "id";
	private static final String VERSAO = "version";
	private static final String ATRIBUTO_DESCRICAO_CIDADE = "descricao_cidade";
	private static final String ATRIBUTO_UF = "unidade_federativa";
	private static final String ATRIBUTO_PAIS = "pais";
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = ATRIBUTO_ID, updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = VERSAO)
	private int version;

	@Column(name = ATRIBUTO_DESCRICAO_CIDADE, nullable = false)
	private String descricaoCidade;

	@Enumerated
	@Column(name = ATRIBUTO_UF, nullable = false)
	private EUf uf;

	@Enumerated
	@Column(name = ATRIBUTO_PAIS, nullable = false)
	private EPais pais;

//	@OneToMany
//	private Set<Bairro> bairros = new HashSet<Bairro>();

	@OneToMany
	@JoinColumn(name = Bairro.ATRIBUTO_ID_LOCALIDADE)
	private Set<Bairro> bairros = new HashSet<Bairro>();
	
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

	public String getDescricaoCidade() {
		return descricaoCidade;
	}

	public void setDescricaoCidade(String descricaoCidade) {
		this.descricaoCidade = descricaoCidade;
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
		if (descricaoCidade != null && !descricaoCidade.trim().isEmpty())
			result += "descricaoCidade: " + descricaoCidade;
		return result;
	}

	public Set<Bairro> getBairros() {
		return this.bairros;
	}

	public void setBairros(final Set<Bairro> bairros) {
		this.bairros = bairros;
	}
}