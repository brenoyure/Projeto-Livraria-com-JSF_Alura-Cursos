package br.com.caelum.livraria.dao;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;

@Stateless
public class AutorDAO {

	@PersistenceContext
	private EntityManager em;
	
	public void adiciona(Autor autor) {
		em.persist(autor);
	}

	public void remove(Autor autor) {
		em.remove(em.merge(autor));
	}

	public void atualiza(Autor autor) {
		em.merge(autor);
	}

	public List<Autor> listaTodos() {
		var query = em.getCriteriaBuilder()
						.createQuery(Autor.class);
		query.from(Autor.class);

		return em
				.createQuery(query)
				.getResultList();
	}

	public Autor buscaPorId(Integer id) {
		return em.find(Autor.class, id);
	}
	
	public Autor buscaRefPorId(Integer id) {
		return em.merge(em.getReference(Autor.class, id));
	}

	public long contaTodos() {
		CriteriaBuilder		cb	= em.getCriteriaBuilder();
		CriteriaQuery<Long> cq	= cb.createQuery(Long.class);
		cq.select(cb.count(cq.from(Livro.class)));
		return em
				.createQuery(cq)
				.getSingleResult();
	}

	public List<Autor> listaTodosPaginada(int firstResult, int maxResults) {
		CriteriaQuery<Autor> query = em.getCriteriaBuilder().createQuery(Autor.class);
		query.select(query.from(Autor.class));

		return em.createQuery(query)
				.setFirstResult(firstResult)
				.setMaxResults(maxResults)
				.getResultList();
	}
	
}
