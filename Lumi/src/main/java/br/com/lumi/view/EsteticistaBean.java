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

import br.com.lumi.model.Esteticista;
import br.lumi.server.enuns.ESituacao;
import br.lumi.server.enuns.ETipoUsuario;

/**
 * Backing bean for Esteticista entities.
 * <p/>
 * This class provides CRUD functionality for all Esteticista entities. It focuses
 * purely on Java EE 6 standards (e.g. <tt>&#64;ConversationScoped</tt> for
 * state management, <tt>PersistenceContext</tt> for persistence,
 * <tt>CriteriaBuilder</tt> for searches) rather than introducing a CRUD framework or
 * custom base class.
 */

@Named
@Stateful
@ConversationScoped
public class EsteticistaBean implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * Support creating and retrieving Esteticista entities
	 */

	private Long id;

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	private Esteticista esteticista;

	public Esteticista getEsteticista() {
		return this.esteticista;
	}

	public void setEsteticista(Esteticista esteticista) {
		this.esteticista = esteticista;
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
			this.esteticista = this.example;
		} else {
			this.esteticista = findById(getId());
		}
	}

	public Esteticista findById(Long id) {

		return this.entityManager.find(Esteticista.class, id);
	}

	/*
	 * Support updating and deleting Esteticista entities
	 */

	public String update() {
		this.conversation.end();

		try {
			if (this.id == null) {
				this.entityManager.persist(this.esteticista);
				return "search?faces-redirect=true";
			} else {
				this.entityManager.merge(this.esteticista);
				return "view?faces-redirect=true&id="
						+ this.esteticista.getId();
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
			Esteticista deletableEntity = findById(getId());

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
	 * Support searching Esteticista entities with pagination
	 */

	private int page;
	private long count;
	private List<Esteticista> pageItems;

	private Esteticista example = new Esteticista();

	public int getPage() {
		return this.page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageSize() {
		return 10;
	}

	public Esteticista getExample() {
		return this.example;
	}

	public void setExample(Esteticista example) {
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
		Root<Esteticista> root = countCriteria.from(Esteticista.class);
		countCriteria = countCriteria.select(builder.count(root)).where(
				getSearchPredicates(root));
		this.count = this.entityManager.createQuery(countCriteria)
				.getSingleResult();

		// Populate this.pageItems

		CriteriaQuery<Esteticista> criteria = builder
				.createQuery(Esteticista.class);
		root = criteria.from(Esteticista.class);
		TypedQuery<Esteticista> query = this.entityManager.createQuery(criteria
				.select(root).where(getSearchPredicates(root)));
		query.setFirstResult(this.page * getPageSize()).setMaxResults(
				getPageSize());
		this.pageItems = query.getResultList();
	}

	private Predicate[] getSearchPredicates(Root<Esteticista> root) {

		CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
		List<Predicate> predicatesList = new ArrayList<Predicate>();

		String nome = this.example.getNome();
		if (nome != null && !"".equals(nome)) {
			predicatesList.add(builder.like(
					builder.lower(root.<String> get("nome")),
					'%' + nome.toLowerCase() + '%'));
		}
		String telefone = this.example.getTelefone();
		if (telefone != null && !"".equals(telefone)) {
			predicatesList.add(builder.like(
					builder.lower(root.<String> get("telefone")),
					'%' + telefone.toLowerCase() + '%'));
		}
		ETipoUsuario tipo = this.example.getTipo();
		if (tipo != null) {
			predicatesList.add(builder.equal(root.get("tipo"), tipo));
		}
		ESituacao situacao = this.example.getSituacao();
		if (situacao != null) {
			predicatesList.add(builder.equal(root.get("situacao"), situacao));
		}
		String senha = this.example.getSenha();
		if (senha != null && !"".equals(senha)) {
			predicatesList.add(builder.like(
					builder.lower(root.<String> get("senha")),
					'%' + senha.toLowerCase() + '%'));
		}

		return predicatesList.toArray(new Predicate[predicatesList.size()]);
	}

	public List<Esteticista> getPageItems() {
		return this.pageItems;
	}

	public long getCount() {
		return this.count;
	}

	/*
	 * Support listing and POSTing back Esteticista entities (e.g. from inside an
	 * HtmlSelectOneMenu)
	 */

	public List<Esteticista> getAll() {

		CriteriaQuery<Esteticista> criteria = this.entityManager
				.getCriteriaBuilder().createQuery(Esteticista.class);
		return this.entityManager.createQuery(
				criteria.select(criteria.from(Esteticista.class)))
				.getResultList();
	}

	@Resource
	private SessionContext sessionContext;

	public Converter getConverter() {

		final EsteticistaBean ejbProxy = this.sessionContext
				.getBusinessObject(EsteticistaBean.class);

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

				return String.valueOf(((Esteticista) value).getId());
			}
		};
	}

	/*
	 * Support adding children to bidirectional, one-to-many tables
	 */

	private Esteticista add = new Esteticista();

	public Esteticista getAdd() {
		return this.add;
	}

	public Esteticista getAdded() {
		Esteticista added = this.add;
		this.add = new Esteticista();
		return added;
	}
}
