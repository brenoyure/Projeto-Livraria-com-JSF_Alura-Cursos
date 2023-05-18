package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;

import br.com.caelum.livraria.modelo.Livro;

@Stateless
public class LivroDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void adiciona(Livro livro) {
		em.persist(livro);
	}

	public void remove(Livro livro) {
		em.remove(em.merge(livro));
	}

	public void atualiza(Livro livro) {
		em.merge(livro);
	}

	public List<Livro> listaTodos() {
		var	cb 		=	em.getCriteriaBuilder();
		var query	=	cb.createQuery(Livro.class);

		query
			.from(Livro.class)
			.fetch("autores");

		return em
				.createQuery(query)
				.getResultList();
	}

	public Livro buscaPorId(Integer id) {
		var	cb 		=	em.getCriteriaBuilder();
		var query	=	cb.createQuery(Livro.class);
		var livro	=	query.from(Livro.class);

		livro.fetch("autores", JoinType.LEFT);

		query.where(cb.equal(livro.get("id"), id));
		return em.createQuery(query).getSingleResult();
		
	}

	public int quantidadeDeElementos() {
		return (int) contaTodos();
	}

	public long contaTodos() {
		CriteriaBuilder		cb	= em.getCriteriaBuilder();
		CriteriaQuery<Long> cq	= cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Livro.class)));
		return em
				.createQuery(cq)
				.getSingleResult();
	}

	public List<Livro> listaTodosPaginada(int firstResult, int maxResults) {
		var cb      = em.getCriteriaBuilder();
		var query   = cb.createQuery(Livro.class);
		var root    = query.from(Livro.class);
		query.select(root);

		root.fetch("autores");

		return em.createQuery(query)
				.setFirstResult(firstResult)
				.setMaxResults(maxResults)
				.getResultList();
	}

	public List<Livro> listaTodosPaginada(int firstResult, int maxResults, String coluna, String valorDigitado) {
		var cb      = em.getCriteriaBuilder();
		var query   = cb.createQuery(Livro.class);
		var root    = query.from(Livro.class);
		query.select(root);

		root.fetch("autores", JoinType.INNER);

		if (valorDigitado != null)
			query.where(
					cb.like(
							root.get(coluna), "%"+valorDigitado+"%"));

		return em.createQuery(query)
				.setFirstResult(firstResult)
				.setMaxResults(maxResults)
				.getResultList();
	}

}
