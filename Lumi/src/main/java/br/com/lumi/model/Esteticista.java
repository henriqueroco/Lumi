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

import br.lumi.server.enuns.ETipoUsuario;

import javax.persistence.Enumerated;

import br.lumi.server.enuns.ESituacao;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = Esteticista.TABELA)
@XmlRootElement
public class Esteticista implements Serializable
{

   private static final long serialVersionUID = 2921423828056896954L;
   static final String TABELA = "esteticista";
   private static final String ATRIBUTO_ID = "id";
   private static final String VERSAO = "version";
   private static final String ATRIBUTO_NOME = "nome";
   private static final String ATRIBUTO_TELEFONE = "numero_telefone";
   private static final String ATRIBUTO_TIPO = "tipo_usuario";
   private static final String ATRIBUTO_SENHA = "senha";
   private static final String ATRIBUTO_SITUACAO = "situacao";

   @Id
   @GeneratedValue(strategy = GenerationType.AUTO)
   @Column(name = ATRIBUTO_ID, updatable = false, nullable = false)
   private Long id;
   @Version
   @Column(name = VERSAO)
   private int version;

   @Column(name = ATRIBUTO_NOME, nullable = false)
   private String nome;

   @Column(name = ATRIBUTO_TELEFONE, nullable = false)
   private String telefone;

   @Column(length = 1024, name = ATRIBUTO_SENHA)
   private String senha;

   @Enumerated
   @Column(name = ATRIBUTO_TIPO, nullable = false, length = 2)
   private ETipoUsuario tipo;

   @Enumerated
   @Column(name = ATRIBUTO_SITUACAO, nullable = false)
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
      if (!(obj instanceof Esteticista))
      {
         return false;
      }
      Esteticista other = (Esteticista) obj;
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

   public String getNome()
   {
      return nome;
   }

   public void setNome(String nome)
   {
      this.nome = nome;
   }

   public String getTelefone()
   {
      return telefone;
   }

   public void setTelefone(String telefone)
   {
      this.telefone = telefone;
   }

   public String getSenha()
   {
      return senha;
   }

   public void setSenha(String senha)
   {
      this.senha = senha;
   }

   public ETipoUsuario getTipo()
   {
      return tipo;
   }

   public void setTipo(ETipoUsuario tipo)
   {
      this.tipo = tipo;
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
      if (nome != null && !nome.trim().isEmpty())
         result += "nome: " + nome;
      if (telefone != null && !telefone.trim().isEmpty())
         result += ", telefone: " + telefone;
      if (senha != null && !senha.trim().isEmpty())
         result += ", senha: " + senha;
      return result;
   }
}