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

import br.com.lumi.model.Logradouro;
import br.com.lumi.model.Bairro;

/**
 * Backing bean for Logradouro entities.
 * <p/>
 * This class provides CRUD functionality for all Logradouro entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class LogradouroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving Logradouro entities
	 */

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Logradouro logradouro;

	public Logradouro getLogradouro() {
		return this.logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
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
			this.logradouro = this.example;
		} else {
			this.logradouro = findById(getId());
		}
	}

	public Logradouro findById(Long id) {

		return this.entityManager.find(Logradouro.class, id);
	}

	/*
	 * Support updating and deleting Logradouro entities
	 */

	public String update() {
		this.conversation.end();

		try {
			if (this.id == null) {
				this.entityManager.persist(this.logradouro);
				return "search?faces-redirect=true";
			} else {
				this.entityManager.merge(this.logradouro);
				return "view?faces-redirect=true&id=" + this.logradouro.getId();
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
			Logradouro deletableEntity = findById(getId());

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
	 * Support searching Logradouro entities with pagination
	 */

	private int page;
	private long count;
	private List<Logradouro> pageItems;

	private Logradouro example = new Logradouro();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public Logradouro getExample() {
		return this.example;
	}

	public void setExample(Logradouro example) {
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
		Root<Logradouro> root = countCriteria.from(Logradouro.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<Logradouro> criteria = builder
				.createQuery(Logradouro.class);
		root = criteria.from(Logradouro.class);
		TypedQuery<Logradouro> query = this.entityManager.createQuery(criteria
				.select(root).where(getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}

	private Predicate[] getSearchPredicates(Root<Logradouro> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		String descricaoLogradouro = this.example.getDescricaoLogradouro();
		if (descricaoLogradouro != null && !"".equals(descricaoLogradouro)) {
			predicatesList.add(builder.like(
					builder.lower(root.<String> get("descricaoLogradouro")),
					'%' + descricaoLogradouro.toLowerCase() + '%'));
		}
		String cep = this.example.getCep();
		if (cep != null && !"".equals(cep)) {
			predicatesList.add(builder.like(
					builder.lower(root.<String> get("cep")),
					'%' + cep.toLowerCase() + '%'));
		}
		Integer numero = this.example.getNumero();
		if (numero != null && numero.intValue() != 0) {
			predicatesList.add(builder.equal(root.get("numero"), numero));
		}
		Bairro bairro = this.example.getBairro();
		if (bairro != null) {
			predicatesList.add(builder.equal(root.get("bairro"), bairro));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<Logradouro> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back Logradouro entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<Logradouro> getAll() {

		CriteriaQuery<Logradouro> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(Logradouro.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(Logradouro.class)))
				.getResultList();
	}

	@Resource
	private SessionContext sessionContext;

	public Converter getConverter() {

		final LogradouroBean ejbProxy = this.sessionContext
				.getBusinessObject(LogradouroBean.class);

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

				return String.valueOf(((Logradouro) value).getId());
			}
		};
	}

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

	private Logradouro add = new Logradouro();

	public Logradouro getAdd() {
		return this.add;
	}

	public Logradouro getAdded() {
		Logradouro added = this.add;
		this.add = new Logradouro();
		return added;
	}
}
