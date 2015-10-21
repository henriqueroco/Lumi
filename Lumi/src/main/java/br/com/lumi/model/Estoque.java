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
import java.util.Date;

import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.lumi.model.Produto;

@Entity
@Table(name = "estoque")
public class Estoque implements Serializable
{

   private static final long serialVersionUID = 1L;

   private static final String ATRIBUTO_ID_PRODUTO = "id_produto";
   private static final String VERSAO = "version";
   private static final String ATRIBUTO_QUANTIDADE = "quantidade";
   private static final String ATRIBUTO_DATA_ALTERACAO = "data_alteracao";
   private static final String ATRIBUTO_ID_MOVIMENTO = "id_movimento";

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = ATRIBUTO_ID_PRODUTO, nullable = false)
   private Produto produto;
   @Version
   @Column(name = VERSAO)
   private int version;

   @Column(name = ATRIBUTO_QUANTIDADE, nullable = false)
   private BigDecimal quantidade;

   @Column(name = ATRIBUTO_DATA_ALTERACAO, nullable = false)
   @Temporal(TemporalType.DATE)
   private Date dataAlteracao;

   @Column(name = ATRIBUTO_ID_MOVIMENTO)
   private Long movimentoEstoque;

   public int getVersion()
   {
      return this.version;
   }

   public void setVersion(final int version)
   {
      this.version = version;
   }

   public BigDecimal getQuantidade()
   {
      return quantidade;
   }

   public void setQuantidade(BigDecimal quantidade)
   {
      this.quantidade = quantidade;
   }

   public Date getDataAlteracao()
   {
      return dataAlteracao;
   }

   public void setDataAlteracao(Date dataAlteracao)
   {
      this.dataAlteracao = dataAlteracao;
   }

   public Long getMovimentoEstoque()
   {
      return movimentoEstoque;
   }

   public void setMovimentoEstoque(Long movimentoEstoque)
   {
      this.movimentoEstoque = movimentoEstoque;
   }

   public Produto getProduto()
   {
      return produto;
   }

   public void setProduto(Produto produto)
   {
      this.produto = produto;
   }

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (movimentoEstoque != null)
         result += "movimentoEstoque: " + movimentoEstoque;
      return result;
   }
}