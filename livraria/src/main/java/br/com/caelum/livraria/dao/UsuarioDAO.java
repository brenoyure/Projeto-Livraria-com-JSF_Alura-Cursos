package br.com.caelum.livraria.dao;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;

import br.com.caelum.livraria.modelo.Usuario;

@Stateless
public class UsuarioDAO {

	@PersistenceContext
	private EntityManager entityManager;

	public boolean usuarioExiste(Usuario usuario) {
		var builder =	entityManager.getCriteriaBuilder();
		var query	=	builder.createQuery(Usuario.class);
		var root	=	query.from(Usuario.class);

		var emailEqualsTo = builder.equal(root.get("email"), usuario.getEmail());
		var senhaEqualsTo = builder.equal(root.get("senha"), usuario.getSenha());

		var emailAndSenhaEqualsTo = builder.and(emailEqualsTo, senhaEqualsTo);

		try {
			return (entityManager
					.createQuery(query
							.where(emailAndSenhaEqualsTo))
							.getSingleResult())!= null;

		} catch (NonUniqueResultException e) {
			return true;

		} catch (NoResultException e) {
			return false;
		}

	}

}
