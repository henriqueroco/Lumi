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

import br.lumi.server.enuns.ESexo;

import javax.persistence.Enumerated;

import br.lumi.server.enuns.ESituacao;
import br.com.lumi.model.Logradouro;

import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "cliente")
@XmlRootElement
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1662149371415257231L;
	private static final String ATRIBUTO_ID = "id";
	private static final String VERSAO = "version";
	private static final String ATRIBUTO_NOME = "nome";
	private static final String ATRIBUTO_CPF = "cpf";
	private static final String ATRIBUTO_DATA_NASCIMENTO = "data_nascimento";
	private static final String ATRIBUTO_SEXO = "sexo";
	private static final String ATRIBUTO_NUMERO_CELULAR = "numero_celular";
	private static final String ATRIBUTO_NUMERO_TELEFONE = "numero_telefone";
	private static final String ATRIBUTO_CONTATO_EMERGENCIA = "contato_emergencia";
	private static final String ATRIBUTO_NUMERO_EMERGENCIA = "numero_emergencia";
	private static final String ATRIBUTO_SITUACAO = "situacao";
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = ATRIBUTO_ID, updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = VERSAO)
	private int version;

	@Column(name = ATRIBUTO_NOME, nullable = false)
	private String nome;

	@Column(name = ATRIBUTO_CPF, nullable = false)
	private String cpf;

	@Column(name = ATRIBUTO_DATA_NASCIMENTO, nullable = false)
	@Temporal(TemporalType.DATE)
	private Date dataNascimento;

	@Enumerated
	@Column(name = ATRIBUTO_SEXO, nullable = false)
	private ESexo sexo;

	@Column(name = ATRIBUTO_NUMERO_CELULAR, nullable = false)
	private String celular;

	@Column(name = ATRIBUTO_NUMERO_TELEFONE)
	private String telefone;

	@Column(name = ATRIBUTO_CONTATO_EMERGENCIA, nullable = false)
	private String contatoEmergencia;

	@Column(name = ATRIBUTO_NUMERO_EMERGENCIA, nullable = false)
	private String telefoneEmergencia;

	@Enumerated
	@Column(name = ATRIBUTO_SITUACAO, nullable = false)
	private ESituacao situacao;

	@ManyToOne
	private Logradouro logradouro;

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
		if (!(obj instanceof Cliente)) {
			return false;
		}
		Cliente other = (Cliente) obj;
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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Date getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(Date dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public ESexo getSexo() {
		return sexo;
	}

	public void setSexo(ESexo sexo) {
		this.sexo = sexo;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getContatoEmergencia() {
		return contatoEmergencia;
	}

	public void setContatoEmergencia(String contatoEmergencia) {
		this.contatoEmergencia = contatoEmergencia;
	}

	public String getTelefoneEmergencia() {
		return telefoneEmergencia;
	}

	public void setTelefoneEmergencia(String telefoneEmergencia) {
		this.telefoneEmergencia = telefoneEmergencia;
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
		if (nome != null && !nome.trim().isEmpty())
			result += "nome: " + nome;
		if (cpf != null && !cpf.trim().isEmpty())
			result += ", cpf: " + cpf;
		if (celular != null && !celular.trim().isEmpty())
			result += ", celular: " + celular;
		if (telefone != null && !telefone.trim().isEmpty())
			result += ", telefone: " + telefone;
		if (contatoEmergencia != null && !contatoEmergencia.trim().isEmpty())
			result += ", contatoEmergencia: " + contatoEmergencia;
		if (telefoneEmergencia != null && !telefoneEmergencia.trim().isEmpty())
			result += ", telefoneEmergencia: " + telefoneEmergencia;
		return result;
	}

	public Logradouro getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(final Logradouro logradouro) {
		this.logradouro = logradouro;
	}
}