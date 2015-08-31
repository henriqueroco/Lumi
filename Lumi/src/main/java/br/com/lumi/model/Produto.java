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

@Entity
@Table(name = "produto")
public class Produto implements Serializable
{

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = "id", updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = "version")
   private int version;

   @Column(length = 80, name = "descricao", nullable = false)
   private String descricao;

   @Column(name = "descricao_marca", nullable = false)
   private String descricaoMarca;

   @Column
   private String tipo;

   @Enumerated
   @Column(name = "tipo_embalagem", nullable = false)
   private ETipoEmbalagem tipoEmbalagem;

   @Column(name = "quantidade_embalagem", nullable = false)
   private BigDecimal quantidadeEmbalagem;

   @Enumerated
   @Column(name = "unidade_medida_embalagem", nullable = false)
   private EUnidadeMedida unidadeMedidaEmbalagem;

   @Enumerated
   @Column(name = "forma_utilizacao", nullable = false)
   private EFormaUtilizacao formaUtilizacao;

   @Enumerated
   @Column(name = "unidade_medida_utilizacao", nullable = false)
   private EUnidadeMedida unidadeMedidaUtilizacao;

   @Column(name = "pontos", nullable = false)
   private BigDecimal pontos;

   @Enumerated
   @Column(name = "situacao", nullable = false)
   private ESituacao situacao;

   public Long getId()
   {
      return this.id;
   }

   public void setId(final Long id)
   {
      this.id = id;
   }

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   @Override
   public boolean equals(Object obj)
   {
      if (this == obj)
      {
         return true;
      }
      if (!(obj instanceof Produto))
      {
         return false;
      }
      Produto other = (Produto) obj;
      if (id != null)
      {
         if (!id.equals(other.id))
         {
            return false;
         }
      }
      return true;
   }

   @Override
   public int hashCode()
   {
      final int prime = 31;
      int result = 1;
      result = prime * result + ((id == null) ? 0 : id.hashCode());
      return result;
   }

   public String getDescricao()
   {
      return descricao;
   }

   public void setDescricao(String descricao)
   {
      this.descricao = descricao;
   }

   public String getDescricaoMarca()
   {
      return descricaoMarca;
   }

   public void setDescricaoMarca(String descricaoMarca)
   {
      this.descricaoMarca = descricaoMarca;
   }

   public String getTipo()
   {
      return tipo;
   }

   public void setTipo(String tipo)
   {
      this.tipo = tipo;
   }

   public ETipoEmbalagem getTipoEmbalagem()
   {
      return tipoEmbalagem;
   }

   public void setTipoEmbalagem(ETipoEmbalagem tipoEmbalagem)
   {
      this.tipoEmbalagem = tipoEmbalagem;
   }

   public BigDecimal getQuantidadeEmbalagem()
   {
      return quantidadeEmbalagem;
   }

   public void setQuantidadeEmbalagem(BigDecimal quantidadeEmbalagem)
   {
      this.quantidadeEmbalagem = quantidadeEmbalagem;
   }

   public EUnidadeMedida getUnidadeMedidaEmbalagem()
   {
      return unidadeMedidaEmbalagem;
   }

   public void setUnidadeMedidaEmbalagem(EUnidadeMedida unidadeMedidaEmbalagem)
   {
      this.unidadeMedidaEmbalagem = unidadeMedidaEmbalagem;
   }

   public EFormaUtilizacao getFormaUtilizacao()
   {
      return formaUtilizacao;
   }

   public void setFormaUtilizacao(EFormaUtilizacao formaUtilizacao)
   {
      this.formaUtilizacao = formaUtilizacao;
   }

   public EUnidadeMedida getUnidadeMedidaUtilizacao()
   {
      return unidadeMedidaUtilizacao;
   }

   public void setUnidadeMedidaUtilizacao(EUnidadeMedida unidadeMedidaUtilizacao)
   {
      this.unidadeMedidaUtilizacao = unidadeMedidaUtilizacao;
   }

   public BigDecimal getPontos()
   {
      return pontos;
   }

   public void setPontos(BigDecimal pontos)
   {
      this.pontos = pontos;
   }

   public ESituacao getSituacao()
   {
      return situacao;
   }

   public void setSituacao(ESituacao situacao)
   {
      this.situacao = situacao;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (descricao != null && !descricao.trim().isEmpty())
         result += "descricao: " + descricao;
      if (descricaoMarca != null && !descricaoMarca.trim().isEmpty())
         result += ", descricaoMarca: " + descricaoMarca;
      if (tipo != null && !tipo.trim().isEmpty())
         result += ", tipo: " + tipo;
      return result;
   }
}