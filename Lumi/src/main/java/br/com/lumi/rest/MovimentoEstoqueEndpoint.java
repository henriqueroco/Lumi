package br.com.lumi.rest;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.OptimisticLockException;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriBuilder;
import br.com.lumi.model.MovimentoEstoque;

/**
 * 
 */
@Stateless
@Path("/movimentoestoques")
public class MovimentoEstoqueEndpoint {
	@PersistenceContext(unitName = "Lumi-persistence-unit")
	private EntityManager em;

	@POST
	@Consumes("application/json")
	public Response create(MovimentoEstoque entity) {
		em.persist(entity);
		return Response.created(
				UriBuilder.fromResource(MovimentoEstoqueEndpoint.class)
						.path(String.valueOf(entity.getId())).build()).build();
	}

	@DELETE
	@Path("/{id:[0-9][0-9]*}")
	public Response deleteById(@PathParam("id") Long id) {
		MovimentoEstoque entity = em.find(MovimentoEstoque.class, id);
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		em.remove(entity);
		return Response.noContent().build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces("application/json")
	public Response findById(@PathParam("id") Long id) {
		TypedQuery<MovimentoEstoque> findByIdQuery = em
				.createQuery(
						"SELECT DISTINCT m FROM MovimentoEstoque m LEFT JOIN FETCH m.produto LEFT JOIN FETCH m.atendimento WHERE m.id = :entityId ORDER BY m.id",
						MovimentoEstoque.class);
		findByIdQuery.setParameter("entityId", id);
		MovimentoEstoque entity;
		try {
			entity = findByIdQuery.getSingleResult();
		} catch (NoResultException nre) {
			entity = null;
		}
		if (entity == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		return Response.ok(entity).build();
	}

	@GET
	@Produces("application/json")
	public List<MovimentoEstoque> listAll(
			@QueryParam("start") Integer startPosition,
			@QueryParam("max") Integer maxResult) {
		TypedQuery<MovimentoEstoque> findAllQuery = em
				.createQuery(
						"SELECT DISTINCT m FROM MovimentoEstoque m LEFT JOIN FETCH m.produto LEFT JOIN FETCH m.atendimento ORDER BY m.id",
						MovimentoEstoque.class);
		if (startPosition != null) {
			findAllQuery.setFirstResult(startPosition);
		}
		if (maxResult != null) {
			findAllQuery.setMaxResults(maxResult);
		}
		final List<MovimentoEstoque> results = findAllQuery.getResultList();
		return results;
	}

	@PUT
	@Path("/{id:[0-9][0-9]*}")
	@Consumes("application/json")
	public Response update(@PathParam("id") Long id, MovimentoEstoque entity) {
		if (entity == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (id == null) {
			return Response.status(Status.BAD_REQUEST).build();
		}
		if (!id.equals(entity.getId())) {
			return Response.status(Status.CONFLICT).entity(entity).build();
		}
		if (em.find(MovimentoEstoque.class, id) == null) {
			return Response.status(Status.NOT_FOUND).build();
		}
		try {
			entity = em.merge(entity);
		} catch (OptimisticLockException e) {
			return Response.status(Response.Status.CONFLICT)
					.entity(e.getEntity()).build();
		}

		return Response.noContent().build();
	}
}
