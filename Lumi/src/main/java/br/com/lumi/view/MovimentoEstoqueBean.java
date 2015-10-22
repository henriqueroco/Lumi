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

import br.com.lumi.model.MovimentoEstoque;
import br.lumi.server.enuns.ETipoMovimentacao;
import br.lumi.server.enuns.EUnidadeMedida;

/**
 * Backing bean for MovimentoEstoque entities.
 * <p/>
 * This class provides CRUD functionality for all MovimentoEstoque entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class MovimentoEstoqueBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving MovimentoEstoque entities
	 */

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private MovimentoEstoque movimentoEstoque;

	public MovimentoEstoque getMovimentoEstoque() {
		return this.movimentoEstoque;
	}

	public void setMovimentoEstoque(MovimentoEstoque movimentoEstoque) {
		this.movimentoEstoque = movimentoEstoque;
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
			this.movimentoEstoque = this.example;
		} else {
			this.movimentoEstoque = findById(getId());
		}
	}

	public MovimentoEstoque findById(Long id) {

		return this.entityManager.find(MovimentoEstoque.class, id);
	}

	/*
	 * Support updating and deleting MovimentoEstoque entities
	 */

	public String update() {
		this.conversation.end();

		try {
			if (this.id == null) {
				this.entityManager.persist(this.movimentoEstoque);
				return "search?faces-redirect=true";
			} else {
				this.entityManager.merge(this.movimentoEstoque);
				return "view?faces-redirect=true&id="
						+ this.movimentoEstoque.getId();
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
			MovimentoEstoque deletableEntity = findById(getId());

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
	 * Support searching MovimentoEstoque entities with pagination
	 */

	private int page;
	private long count;
	private List<MovimentoEstoque> pageItems;

	private MovimentoEstoque example = new MovimentoEstoque();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public MovimentoEstoque getExample() {
		return this.example;
	}

	public void setExample(MovimentoEstoque example) {
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
		Root<MovimentoEstoque> root = countCriteria
				.from(MovimentoEstoque.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<MovimentoEstoque> criteria = builder
				.createQuery(MovimentoEstoque.class);
		root = criteria.from(MovimentoEstoque.class);
		TypedQuery<MovimentoEstoque> query = this.entityManager
				.createQuery(criteria.select(root).where(
						getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}

	private Predicate[] getSearchPredicates(Root<MovimentoEstoque> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		ETipoMovimentacao tipoMovimento = this.example.getTipoMovimento();
		if (tipoMovimento != null) {
			predicatesList.add(builder.equal(root.get("tipoMovimento"),
					tipoMovimento));
		}
		EUnidadeMedida unidadeMedidaMovimentacao = this.example
				.getUnidadeMedidaMovimentacao();
		if (unidadeMedidaMovimentacao != null) {
			predicatesList.add(builder.equal(
					root.get("unidadeMedidaMovimentacao"),
					unidadeMedidaMovimentacao));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<MovimentoEstoque> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back MovimentoEstoque entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<MovimentoEstoque> getAll() {

		CriteriaQuery<MovimentoEstoque> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(MovimentoEstoque.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(MovimentoEstoque.class)))
				.getResultList();
	}

	@Resource
	private SessionContext sessionContext;

	public Converter getConverter() {

		final MovimentoEstoqueBean ejbProxy = this.sessionContext
				.getBusinessObject(MovimentoEstoqueBean.class);

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

				return String.valueOf(((MovimentoEstoque) value).getId());
			}
		};
	}

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

	private MovimentoEstoque add = new MovimentoEstoque();

	public MovimentoEstoque getAdd() {
		return this.add;
	}

	public MovimentoEstoque getAdded() {
		MovimentoEstoque added = this.add;
		this.add = new MovimentoEstoque();
		return added;
	}
}
