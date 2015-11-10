package br.com.lumi.model;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

import br.lumi.server.enuns.ESituacaoAtendimento;
import br.com.lumi.model.Cliente;
import javax.persistence.ManyToOne;
import javax.persistence.FetchType;
import br.com.lumi.model.Esteticista;
@Entity
@Table(name = Atendimento.TABELA)
@XmlRootElement
public class Atendimento implements Serializable {

	static final String TABELA = "atendimento";
	private static final String ATRIBUTO_ID = "id";
	private static final String ATRIBUTO_VERSION = "version";
	private static final String ATRIBUTO_DATA = "data";
	private static final String ATRIBUTO_HORA_INICIO = "hora_inicio";
	private static final String ATRIBUTO_HORA_FIM = "hora_fim";

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = ATRIBUTO_ID, updatable = false, nullable = false)
	private Long id;
	@Version
	@Column(name = ATRIBUTO_VERSION)
	private int version;

	@Column(name = ATRIBUTO_DATA, nullable = false)
	@Temporal(TemporalType.DATE)
	private Date data;

	@Column(name = ATRIBUTO_HORA_INICIO, nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horaInicio;

	@Column(name = ATRIBUTO_HORA_FIM, nullable = false)
	@Temporal(TemporalType.TIME)
	private Date horaFim;

	@Enumerated
	private ESituacaoAtendimento situacao;

	@OneToMany
	private Set<Procedimento> procedimento = new HashSet<Procedimento>();

	@ManyToOne(fetch = FetchType.LAZY)
	private Cliente cliente;

	@ManyToOne(fetch = FetchType.LAZY)
	private Esteticista esteticista;

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
		if (!(obj instanceof Atendimento)) {
			return false;
		}
		Atendimento other = (Atendimento) obj;
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

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Date getHoraInicio() {
		return horaInicio;
	}

	public void setHoraInicio(Date horaInicio) {
		this.horaInicio = horaInicio;
	}

	public Date getHoraFim() {
		return horaFim;
	}

	public void setHoraFim(Date horaFim) {
		this.horaFim = horaFim;
	}

	public ESituacaoAtendimento getSituacao() {
		return situacao;
	}

	public void setSituacao(ESituacaoAtendimento situacao) {
		this.situacao = situacao;
	}

	public Set<Procedimento> getProcedimento() {
		return this.procedimento;
	}

	public void setProcedimento(final Set<Procedimento> procedimento) {
		this.procedimento = procedimento;
	}

	public Cliente getCliente() {
		return this.cliente;
	}

	public void setCliente(final Cliente cliente) {
		this.cliente = cliente;
	}

	public Esteticista getEsteticista() {
		return this.esteticista;
	}

	public void setEsteticista(final Esteticista esteticista) {
		this.esteticista = esteticista;
	}
}