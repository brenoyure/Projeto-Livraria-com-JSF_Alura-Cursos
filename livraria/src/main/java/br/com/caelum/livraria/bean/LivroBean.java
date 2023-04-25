package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;
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

	@Getter	@Setter
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
//			throw new RuntimeException("Livro deve ter pelo menos um Autor.");
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
			
			return;
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

	public List<Livro> getLivrosCadastrados() {
		return livroDao.listaTodos();
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();

		if (!valor.startsWith("1"))
			throw new ValidatorException(new FacesMessage("ISBN deve começar com número 1."));

	}

	/**
	 * Redireciona para o formulário de cadastro do Autor.
	 * @return autor.xhtml
	 */
	public String formAutor() {
		return "autor";
	}

}
