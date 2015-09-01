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
import java.math.BigDecimal;

import br.com.lumi.model.Produto;

import javax.persistence.ManyToOne;

import br.com.lumi.model.Procedimento;

@Entity
@Table(name = ProdutoProcedimento.TABELA)
public class ProdutoProcedimento implements Serializable {

	private static final long serialVersionUID = 1L;

	static final String TABELA = "produto_por_procedimento";
	private static final String ATRIBUTO_ID = "id";
	private static final String VERSAO = "version";
	private static final String ATRIBUTO_QUANTIDADE = "quantidade";
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = ATRIBUTO_ID, updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = VERSAO)
	private int version;

	@Column(name = ATRIBUTO_QUANTIDADE, nullable = false)
	private BigDecimal quantidade;

	@ManyToOne
	private Produto produto;

	@ManyToOne
	private Procedimento procedimento;

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
		if (!(obj instanceof ProdutoProcedimento)) {
			return false;
		}
		ProdutoProcedimento other = (ProdutoProcedimento) obj;
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

	public BigDecimal getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(BigDecimal quantidade) {
		this.quantidade = quantidade;
	}

	public Produto getProduto() {
		return this.produto;
	}

	public void setProduto(final Produto produto) {
		this.produto = produto;
	}

	public Procedimento getProcedimento() {
		return this.procedimento;
	}

	public void setProcedimento(final Procedimento procedimento) {
		this.procedimento = procedimento;
	}
}