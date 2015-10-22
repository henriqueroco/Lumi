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

import br.com.lumi.model.Localidade;
import br.lumi.server.enuns.EPais;
import br.lumi.server.enuns.EUf;

/**
 * Backing bean for Localidade entities.
 * <p/>
 * This class provides CRUD functionality for all Localidade entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class LocalidadeBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving Localidade entities
	 */

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Localidade localidade;

	public Localidade getLocalidade() {
		return this.localidade;
	}

	public void setLocalidade(Localidade localidade) {
		this.localidade = localidade;
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
			this.localidade = this.example;
		} else {
			this.localidade = findById(getId());
		}
	}

	public Localidade findById(Long id) {

		return this.entityManager.find(Localidade.class, id);
	}

	/*
	 * Support updating and deleting Localidade entities
	 */

	public String update() {
		this.conversation.end();

		try {
			if (this.id == null) {
				this.entityManager.persist(this.localidade);
				return "search?faces-redirect=true";
			} else {
				this.entityManager.merge(this.localidade);
				return "view?faces-redirect=true&id=" + this.localidade.getId();
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
			Localidade deletableEntity = findById(getId());

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
	 * Support searching Localidade entities with pagination
	 */

	private int page;
	private long count;
	private List<Localidade> pageItems;

	private Localidade example = new Localidade();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public Localidade getExample() {
		return this.example;
	}

	public void setExample(Localidade example) {
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
		Root<Localidade> root = countCriteria.from(Localidade.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<Localidade> criteria = builder
				.createQuery(Localidade.class);
		root = criteria.from(Localidade.class);
		TypedQuery<Localidade> query = this.entityManager.createQuery(criteria
				.select(root).where(getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}

	private Predicate[] getSearchPredicates(Root<Localidade> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		String nomeCidade = this.example.getNomeCidade();
		if (nomeCidade != null && !"".equals(nomeCidade)) {
			predicatesList.add(builder.like(
					builder.lower(root.<String> get("nomeCidade")),
					'%' + nomeCidade.toLowerCase() + '%'));
		}
		EUf uf = this.example.getUf();
		if (uf != null) {
			predicatesList.add(builder.equal(root.get("uf"), uf));
		}
		EPais pais = this.example.getPais();
		if (pais != null) {
			predicatesList.add(builder.equal(root.get("pais"), pais));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<Localidade> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back Localidade entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<Localidade> getAll() {

		CriteriaQuery<Localidade> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(Localidade.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(Localidade.class)))
				.getResultList();
	}

	@Resource
	private SessionContext sessionContext;

	public Converter getConverter() {

		final LocalidadeBean ejbProxy = this.sessionContext
				.getBusinessObject(LocalidadeBean.class);

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

				return String.valueOf(((Localidade) value).getId());
			}
		};
	}

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

	private Localidade add = new Localidade();

	public Localidade getAdd() {
		return this.add;
	}

	public Localidade getAdded() {
		Localidade added = this.add;
		this.add = new Localidade();
		return added;
	}
}
