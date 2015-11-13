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

import br.lumi.server.enuns.ETipoEmbalagem;

import javax.persistence.Enumerated;

import java.math.BigDecimal;

import br.lumi.server.enuns.EUnidadeMedida;
import br.lumi.server.enuns.EFormaUtilizacao;
import br.lumi.server.enuns.ESituacao;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = Produto.TABELA)
@XmlRootElement
public class Produto implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2545463717178402165L;
	static final String TABELA = "produto";
	private static final String ATRIBUTO_ID = "id";
	private static final String ATRIBUTO_VERSION = "version";
	private static final String ATRIBUTO_DESCRICAO = "descricao";
	private static final String ATRIBUTO_DESCRICAO_MARCA = "descricao_marca";
	private static final String ATRIBUTO_TIPO_EMBALAGEM = "tipo_embalagem";
	private static final String ATRIBUTO_QUANTIDADE_EMBALAGEM = "quantidade_embalagem";
	private static final String ATRIBUTO_UNIDADE_MEDIDA_EMBALAGEM = "unidade_medida_embalagem";
	private static final String ATRIBUTO_FORMA_UTILIZACAO = "forma_utilizacao";
	private static final String ATRIBUTO_UNIDADE_MEDIDA_UTILIZACAO = "unidade_medida_utilizacao";
	private static final String ATRIBUTO_PONTOS = "pontos";
	private static final String ATRIBUTO_SITUACAO = "situacao";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = ATRIBUTO_ID, updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = ATRIBUTO_VERSION)
	private int version;

	@Column(length = 50, name = ATRIBUTO_DESCRICAO, nullable = false)
	private String descricao;

	@Column(length = 50, name = ATRIBUTO_DESCRICAO_MARCA, nullable = false)
	private String descricaoMarca;

	@Enumerated
	@Column(name = ATRIBUTO_TIPO_EMBALAGEM, nullable = false, length = 2)
	private ETipoEmbalagem tipoEmbalagem;

	@Column(name = ATRIBUTO_QUANTIDADE_EMBALAGEM, nullable = false)
	private BigDecimal quantidadeEmbalagem;

	@Enumerated
	@Column(name = ATRIBUTO_UNIDADE_MEDIDA_EMBALAGEM, nullable = false)
	private EUnidadeMedida unidadeMedidaEmbalagem;

	@Enumerated
	@Column(name = ATRIBUTO_FORMA_UTILIZACAO, nullable = false)
	private EFormaUtilizacao formaUtilizacao;

	@Enumerated
	@Column(name = ATRIBUTO_UNIDADE_MEDIDA_UTILIZACAO)
	private EUnidadeMedida unidadeMedidaUtilizacao;

	@Column(name = ATRIBUTO_PONTOS, nullable = false)
	private BigDecimal pontos;

	@Enumerated
	@Column(name = ATRIBUTO_SITUACAO, nullable = false)
	private ESituacao situacao;

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
		if (!(obj instanceof Produto)) {
			return false;
		}
		Produto other = (Produto) obj;
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

	public String getDescricaoMarca() {
		return descricaoMarca;
	}

	public void setDescricaoMarca(String descricaoMarca) {
		this.descricaoMarca = descricaoMarca;
	}

	public ETipoEmbalagem getTipoEmbalagem() {
		return tipoEmbalagem;
	}

	public void setTipoEmbalagem(ETipoEmbalagem tipoEmbalagem) {
		this.tipoEmbalagem = tipoEmbalagem;
	}

	public BigDecimal getQuantidadeEmbalagem() {
		return quantidadeEmbalagem;
	}

	public void setQuantidadeEmbalagem(BigDecimal quantidadeEmbalagem) {
		this.quantidadeEmbalagem = quantidadeEmbalagem;
	}

	public EUnidadeMedida getUnidadeMedidaEmbalagem() {
		return unidadeMedidaEmbalagem;
	}

	public void setUnidadeMedidaEmbalagem(EUnidadeMedida unidadeMedidaEmbalagem) {
		this.unidadeMedidaEmbalagem = unidadeMedidaEmbalagem;
	}

	public EFormaUtilizacao getFormaUtilizacao() {
		return formaUtilizacao;
	}

	public void setFormaUtilizacao(EFormaUtilizacao formaUtilizacao) {
		this.formaUtilizacao = formaUtilizacao;
	}

	public EUnidadeMedida getUnidadeMedidaUtilizacao() {
		return unidadeMedidaUtilizacao;
	}

	public void setUnidadeMedidaUtilizacao(
			EUnidadeMedida unidadeMedidaUtilizacao) {
		this.unidadeMedidaUtilizacao = unidadeMedidaUtilizacao;
	}

	public BigDecimal getPontos() {
		return pontos;
	}

	public void setPontos(BigDecimal pontos) {
		this.pontos = pontos;
	}

	public ESituacao getSituacao() {
		return situacao;
	}

	public void setSituacao(ESituacao situacao) {
		this.situacao = situacao;
	}

	@Override
	public String toString() {
		String result = getClass().getSimpleName() + " ";
		if (descricao != null && !descricao.trim().isEmpty())
			result += "descricao: " + descricao;
		if (descricaoMarca != null && !descricaoMarca.trim().isEmpty())
			result += ", descricaoMarca: " + descricaoMarca;
		return result;
	}
}