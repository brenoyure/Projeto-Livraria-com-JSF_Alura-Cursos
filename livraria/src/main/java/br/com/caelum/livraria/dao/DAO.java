package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

@Stateless
public class DAO<T> {

	@PersistenceContext
	private EntityManager em;
	
	private Class<T> classe;

	public void adiciona(T t) {
		em.persist(t);
	}

	public void remove(T t) {
		em.remove(em.merge(t));
	}

	public void atualiza(T t) {
		em.merge(t);
	}

	public List<T> listaTodos() {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));
		return em.createQuery(query).getResultList();
	}

	public T buscaPorId(Integer id) {
		return em.find(classe, id);
	}

	public long contaTodos() {
		CriteriaBuilder		cb	= em.getCriteriaBuilder();
		CriteriaQuery<Long> cq	= cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(classe)));
		return em
				.createQuery(cq).getSingleResult();
	}

	public List<T> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<T> query = em.getCriteriaBuilder().createQuery(classe);
		query.select(query.from(classe));

		return em.createQuery(query)
				.setFirstResult(firstResult)
				.setMaxResults(maxResults)
				.getResultList();
	}

}
