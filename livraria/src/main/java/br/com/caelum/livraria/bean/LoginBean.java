package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.caelum.livraria.dao.UsuarioDAO;
import br.com.caelum.livraria.modelo.Usuario;
import lombok.Getter;

/**
 * Bean responsável pelo Login.
 * 
 * @author breno.brito
 *
 */
@Named
@ViewScoped
public class LoginBean implements Serializable {

	/**
	 * Necessário para que o Bean funcione, com o escopo maior que RequestScoped.
	 */
	private static final long serialVersionUID = 1L;

	@Inject
	private UsuarioDAO usuarioDAO;

	@Getter
	private Usuario usuario = new Usuario();

	/**
	 * Efetua o Login do usuário, verificando antes se este existe ou não no banco de dados.
	 * Caso o usuário exista, este é adicionado na Session, 
	 * através do getSessionMap() do External Context do FacesContext.
	 * 
	 * @return redirect to livro.xhtml
	 */
	public String efetuarLogin() {

		if (usuarioExiste(usuario)) {
			System.out.println("Efetuando Login do Usuário " + usuario.getEmail());

			FacesContext.getCurrentInstance()
							.getExternalContext()
							.getSessionMap()
							.put("usuarioLogado", this.usuario);

			return "livro?faces-redirect=true";

		}

		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Erro ao efetuar login, usuário ou senha incorretos."));
		System.err.println("Erro ao efetuar login, usuário ou senha incorretos.");
		return null;

	}

	private boolean usuarioExiste(Usuario usuario) {
		return usuarioDAO.usuarioExiste(usuario);
	}

}
