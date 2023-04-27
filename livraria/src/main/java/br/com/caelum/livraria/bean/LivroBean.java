package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

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
import br.com.caelum.livraria.util.ForwardView;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class LivroBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String LIVRO_VIEW = "livro";

	@Getter	@Setter
	private Integer autorId;

	@Getter
	private Livro livro = new Livro();

	@Inject
	private LivroDAO livroDao;

	@Inject
	private AutorDAO autorDao;

	public ForwardView gravar() {

		if (livro.getAutores().isEmpty()) {
			FacesContext.getCurrentInstance().addMessage("autor", new FacesMessage("Livro deve ter pelo menos um Autor."));
			return null;
		}

		if (livro.getId() == null)
			livroDao.adiciona(this.livro);

		else
			livroDao.atualiza(livro);

		this.livro = new Livro();
		return new ForwardView(LIVRO_VIEW);
	}
	
	public void remover(Livro livro) {
		livroDao.remove(livro);
	}
	
	public void removerAutorDoLivro(Autor autor) {
		this.livro.removerAutor(autor);
	}
	
	public void exibir(Livro livro) {
		this.livro = livroDao.buscaPorId(livro.getId());
	}

	public void gravarAutor() {
		Autor autor = autorDao.buscaPorId(autorId);
		livro.adicionaAutor(autor);
	}

	public Set<Autor> getAutoresDoLivro() {
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
