package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.servico.Service;

@Named
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;
	private Autor autor = new Autor();

	@Inject
	private Service<Autor> servico;
	
	public Autor getAutor() {
		return autor;
	}

	public void gravar() {
		System.out.println("Gravando autor " + this.autor.getNome());
		servico.salvar(this.autor);
	}
}
