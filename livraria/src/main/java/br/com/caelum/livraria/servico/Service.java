package br.com.caelum.livraria.servico;

import javax.ejb.Stateless;
import javax.inject.Inject;

import br.com.caelum.livraria.dao.DAO;

@Stateless
public class Service<T> {

	@Inject
	private DAO<T> dao;

	public void salvar(T t) {
		dao.adiciona(t);
	}
	
}
