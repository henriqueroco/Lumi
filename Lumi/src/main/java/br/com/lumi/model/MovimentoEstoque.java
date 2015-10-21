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
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.lumi.server.enuns.ETipoMovimentacao;

import javax.persistence.Enumerated;

import java.math.BigDecimal;

import br.lumi.server.enuns.EUnidadeMedida;

@Entity
@Table(name = "movimento_estoque")
public class MovimentoEstoque implements Serializable
{

   private static final String ATRIBUTO_ID = "id";
   private static final String ATRIBUTO_VERSION = "version";
   private static final String ATRIBUTO_DATA_MOVIMENTACAO = "data_movimentacao";
   private static final String ATRIBUTO_TIPO_MOVIMENTACAO = "tipo_movimentacao";
   private static final String ATRIBUTO_QUANTIDADE = "quantidade";
   private static final String ATRIBUTO_UNIDADE_MEDIDA_MOVIMENTACAO = "unidade_medida_movimentacao";
   private static final String ATRIBUTO_VALOR = "valor";

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = ATRIBUTO_ID, updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = ATRIBUTO_VERSION)
   private int version;

   @Column(name = ATRIBUTO_DATA_MOVIMENTACAO, nullable = false)
   @Temporal(TemporalType.DATE)
   private Date dataMovimento;

   @Enumerated
   @Column(name = ATRIBUTO_TIPO_MOVIMENTACAO, nullable = false)
   private ETipoMovimentacao tipoMovimento;

   @Column(name = ATRIBUTO_QUANTIDADE, nullable = false)
   private BigDecimal quantidade;

   @Enumerated
   @Column(name = ATRIBUTO_UNIDADE_MEDIDA_MOVIMENTACAO, nullable = false)
   private EUnidadeMedida unidadeMedidaMovimentacao;

   @Column(name = ATRIBUTO_VALOR)
   private BigDecimal valor;

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
      if (!(obj instanceof MovimentoEstoque))
      {
         return false;
      }
      MovimentoEstoque other = (MovimentoEstoque) obj;
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

   public Date getDataMovimento()
   {
      return dataMovimento;
   }

   public void setDataMovimento(Date dataMovimento)
   {
      this.dataMovimento = dataMovimento;
   }

   public ETipoMovimentacao getTipoMovimento()
   {
      return tipoMovimento;
   }

   public void setTipoMovimento(ETipoMovimentacao tipoMovimento)
   {
      this.tipoMovimento = tipoMovimento;
   }

   public BigDecimal getQuantidade()
   {
      return quantidade;
   }

   public void setQuantidade(BigDecimal quantidade)
   {
      this.quantidade = quantidade;
   }

   public EUnidadeMedida getUnidadeMedidaMovimentacao()
   {
      return unidadeMedidaMovimentacao;
   }

   public void setUnidadeMedidaMovimentacao(
         EUnidadeMedida unidadeMedidaMovimentacao)
   {
      this.unidadeMedidaMovimentacao = unidadeMedidaMovimentacao;
   }

   public BigDecimal getValor()
   {
      return valor;
   }

   public void setValor(BigDecimal valor)
   {
      this.valor = valor;
   }
}