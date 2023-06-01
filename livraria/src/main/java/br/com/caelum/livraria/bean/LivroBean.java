package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
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

	@Getter @Setter
	private Livro livro = new Livro();

	@Inject
	private LivroDAO livroDao;

	private List<Livro> livros;
	
	@Inject @Getter @Setter
	private LivroLazyDataModel dataModel;

	@Inject
	private AutorDAO autorDao;

	@Inject
	private FacesContext context;


	public ForwardView gravar() {

		if (livro.getAutores().isEmpty()) {
			context.addMessage("autor", new FacesMessage(FacesMessage.SEVERITY_WARN, "Livro deve ter pelo menos um Autor.", null));
			return null;
		}

		if (livro.getId() == null) {
			livroDao.adiciona(this.livro);
			livros.add(livro);
		}

		else {
			livroDao.atualiza(livro);
			livros = livroDao.listaTodos();
		}

		this.livro = new Livro();
		return new ForwardView(LIVRO_VIEW);
	}
	
	public void remover(Livro livro) {
		livroDao.remove(livro);
		livros.remove(livro);
	}
	
	public void removerAutorDoLivro(Autor autor) {
		this.livro.removerAutor(autor);
	}
	
	public void gravarAutor() {
		Autor autor = autorDao.buscaPorId(autorId);
		livro.adicionaAutor(autor);
	}

	public Set<Autor> getAutoresDoLivro() {
		return this.livro.getAutores();
	}

	public List<Livro> getLivrosCadastrados() {
		if (livros == null)
			return livros = livroDao.listaTodos();

		return livros;
	}

	public void comecaComDigitoUm(FacesContext fc, UIComponent component, Object value) throws ValidatorException {
		String valor = value.toString();

		if (!valor.startsWith("1"))
			throw new ValidatorException(new FacesMessage("ISBN deve começar com número 1."));

	}

	public boolean filtraPorPrecoMenorQue(Object valorColuna, Object valorDigitado, Locale locale) {
		String textoDigitado = (valorDigitado == null) ? null : valorDigitado.toString().trim();

		if (textoDigitado == null || textoDigitado.isBlank())
			return true;

		if (valorColuna == null)
			return false;

		try {
			Double precoColuna   =  (Double) valorColuna;
			Double precoDigitado =  Double.valueOf(textoDigitado);
			return precoColuna.compareTo(precoDigitado) < 0;

		} catch (NumberFormatException e) {
			return false;
		}

	}

	/**
	 * Redireciona para o formulário de cadastro do Autor.
	 * @return autor.xhtml
	 */
	public String formAutor() {
		return "autor";
	}

}
