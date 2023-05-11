package br.com.caelum.livraria.bean;

import java.io.Serializable;
import java.util.List;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.AutorDAO;
import br.com.caelum.livraria.modelo.Autor;
import br.com.caelum.livraria.util.RedirectView;
import lombok.Getter;
import lombok.Setter;

@Named
@ViewScoped
public class AutorBean implements Serializable {

	private static final long serialVersionUID = 1L;

	@Getter @Setter
	private Autor autor = new Autor();

	@Getter @Setter
	private Integer autorId;

	@Inject
	private AutorDAO autorDao;
	
	private List<Autor> autores;

	public RedirectView gravar() {

		if (autor.getId() == null) {
			autorDao.adiciona(this.autor);
			return new RedirectView("livro");
		}

		else {
			autorDao.atualiza(autor);
			return new RedirectView("autor");
		}

	}

	public List<Autor> getAutores() {
		if (autores == null)
			return autores = autorDao.listaTodos();
		return autores;
	}

	public void exibir(Autor autor) {
		this.autor = autor;
	}

	public void carregarAutorPeloId() {
		this.autor = autorDao.buscaPorId(autorId);
	}

	public void remover(Autor autor) {
		autorDao.remove(autor);
	}

}
