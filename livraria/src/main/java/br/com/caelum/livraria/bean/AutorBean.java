package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDAO;
import br.com.caelum.livraria.modelo.Autor;

@Named
@RequestScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	public Autor getAutor() {
		return autor;
	}

	private Autor autor = new Autor();

	@Inject
	private AutorDAO autorDao;
	
	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		autorDao.adiciona(this.autor);
	}
	
	public List<Autor> getAutores() {
		return autorDao.listaTodos();
	}
	
}
