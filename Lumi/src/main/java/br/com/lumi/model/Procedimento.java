package br.com.lumi.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = Procedimento.TABELA)
@XmlRootElement
public class Procedimento implements Serializable
{

   private static final long serialVersionUID = 1L;

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

   @ManyToMany
   private Set<Produto> produtos = new HashSet<Produto>();

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
      if (!(obj instanceof Procedimento))
      {
         return false;
      }
      Procedimento other = (Procedimento) obj;
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

   @Override
   public String toString()
   {
      String result = getClass().getSimpleName() + " ";
      if (descricao != null && !descricao.trim().isEmpty())
         result += "descricao: " + descricao;
      return result;
   }

   public Set<Produto> getProdutos()
   {
      return this.produtos;
   }

   public void setProdutos(final Set<Produto> produtos)
   {
      this.produtos = produtos;
   }
}