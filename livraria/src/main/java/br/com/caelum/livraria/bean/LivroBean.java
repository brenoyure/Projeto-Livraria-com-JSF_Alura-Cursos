package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDAO;
import br.com.caelum.livraria.dao.LivroDAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.modelo.Livro;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private Integer autorId;
	
	@Getter
	private Livro livro = new Livro();

	@Inject
	private LivroDAO livroDao;
	
	@Inject
	private AutorDAO autorDao;

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			throw new RuntimeException("Livro deve ter pelo menos um Autor.");
		}

		livroDao.adiciona(this.livro);
	}

	public void gravarAutor() {
		Autor autor = autorDao.buscaPorId(autorId);
		livro.adicionaAutor(autor);
	}

	public List<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

}
