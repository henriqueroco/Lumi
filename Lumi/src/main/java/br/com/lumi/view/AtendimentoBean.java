package br.com.lumi.view;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateful;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import br.com.lumi.model.Atendimento;
import br.lumi.server.enuns.ESituacaoAtendimento;

/**
 * Backing bean for Atendimento entities.
 * <p/>
 * This class provides CRUD functionality for all Atendimento entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class AtendimentoBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving Atendimento entities
	 */

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Atendimento atendimento;

	public Atendimento getAtendimento() {
		return this.atendimento;
	}

	public void setAtendimento(Atendimento atendimento) {
		this.atendimento = atendimento;
	}

	@Inject
	private Conversation conversation;

	@PersistenceContext(unitName = "Lumi-persistence-unit", type = PersistenceContextType.EXTENDED)
	private EntityManager entityManager;

	public String create() {

		this.conversation.begin();
		this.conversation.setTimeout(1800000L);
		return "create?faces-redirect=true";
	}

	public void retrieve() {

		if (FacesContext.getCurrentInstance().isPostback()) {
			return;
		}

		if (this.conversation.isTransient()) {
			this.conversation.begin();
			this.conversation.setTimeout(1800000L);
		}

		if (this.id == null) {
			this.atendimento = this.example;
		} else {
			this.atendimento = findById(getId());
		}
	}

	public Atendimento findById(Long id) {

		return this.entityManager.find(Atendimento.class, id);
	}

	/*
	 * Support updating and deleting Atendimento entities
	 */

	public String update() {
		this.conversation.end();

		try {
			if (this.id == null) {
				this.entityManager.persist(this.atendimento);
				return "search?faces-redirect=true";
			} else {
				this.entityManager.merge(this.atendimento);
				return "view?faces-redirect=true&id="
						+ this.atendimento.getId();
			}
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}

	public String delete() {
		this.conversation.end();

		try {
			Atendimento deletableEntity = findById(getId());

			this.entityManager.remove(deletableEntity);
			this.entityManager.flush();
			return "search?faces-redirect=true";
		} catch (Exception e) {
			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(e.getMessage()));
			return null;
		}
	}

	/*
	 * Support searching Atendimento entities with pagination
	 */

	private int page;
	private long count;
	private List<Atendimento> pageItems;

	private Atendimento example = new Atendimento();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public Atendimento getExample() {
		return this.example;
	}

	public void setExample(Atendimento example) {
		this.example = example;
	}

	public String search() {
		this.page = 0;
		return null;
	}

	public void paginate() {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();

		// Populate this.count

		CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
		Root<Atendimento> root = countCriteria.from(Atendimento.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<Atendimento> criteria = builder
				.createQuery(Atendimento.class);
		root = criteria.from(Atendimento.class);
		TypedQuery<Atendimento> query = this.entityManager.createQuery(criteria
				.select(root).where(getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}

	private Predicate[] getSearchPredicates(Root<Atendimento> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		ESituacaoAtendimento situacao = this.example.getSituacao();
		if (situacao != null) {
			predicatesList.add(builder.equal(root.get("situacao"), situacao));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<Atendimento> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back Atendimento entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<Atendimento> getAll() {

		CriteriaQuery<Atendimento> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(Atendimento.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(Atendimento.class)))
				.getResultList();
	}

	@Resource
	private SessionContext sessionContext;

	public Converter getConverter() {

		final AtendimentoBean ejbProxy = this.sessionContext
				.getBusinessObject(AtendimentoBean.class);

		return new Converter() {

			@Override
			public Object getAsObject(FacesContext context,
					UIComponent component, String value) {

				return ejbProxy.findById(Long.valueOf(value));
			}

			@Override
			public String getAsString(FacesContext context,
					UIComponent component, Object value) {

				if (value == null) {
					return "";
				}

				return String.valueOf(((Atendimento) value).getId());
			}
		};
	}

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

	private Atendimento add = new Atendimento();

	public Atendimento getAdd() {
		return this.add;
	}

	public Atendimento getAdded() {
		Atendimento added = this.add;
		this.add = new Atendimento();
		return added;
	}
}
