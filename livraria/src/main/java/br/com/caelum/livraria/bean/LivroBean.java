package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.modelo.Livro;
import br.com.caelum.livraria.servico.Service;

@Named
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Livro livro = new Livro();
	
	@Inject
	private Service<Livro> servico;

	public Livro getLivro() {
		return livro;
	}

	public void gravar() {
		System.out.println("Gravando livro " + this.livro.getTitulo());

		if (livro.getAutores().isEmpty()) {
			throw new RuntimeException("Livro deve ter pelo menos um Autor.");
		}

		servico.salvar(this.livro);
	}

}
