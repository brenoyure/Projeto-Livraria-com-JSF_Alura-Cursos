package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.modelo.Autor;

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
		CriteriaQuery<Livro> query = em.getCriteriaBuilder().createQuery(Livro.class);
		query.select(query.from(Livro.class));

		return em
				.createQuery(query)
				.getResultList();
	}

	public Autor buscaPorId(Integer id) {
		return em.find(Autor.class, id);
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
		CriteriaQuery<Livro> query = em.getCriteriaBuilder().createQuery(Livro.class);
		query.select(query.from(Livro.class));

		return em.createQuery(query)
				.setFirstResult(firstResult)
				.setMaxResults(maxResults)
				.getResultList();
	}

}
