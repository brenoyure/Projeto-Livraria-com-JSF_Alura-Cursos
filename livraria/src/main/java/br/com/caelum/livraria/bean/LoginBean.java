package br.com.caelum.livraria.bean;

import static javax.faces.application.FacesMessage.SEVERITY_ERROR;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
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

	@Inject
	private FacesContext context;

	/**
	 * <p>
	 *	Efetua o Login do usuário, verificando antes se este existe ou não no banco de dados.
	 * </p>
	 * 
	 * <p>
	 * 	Caso o usuário exista, este é adicionado na Session, 
	 * 	através do getSessionMap() do External Context do FacesContext.
	 * </p>
	 * 
	 * <p>
	 * 	Foi utilizado o Flash através do ExternalContext, para manter as mensagens, durante o redirect, 
	 * 	caso no momento do login, o usuário erre o e-mail ou senha.
	 * </p>
	 * 
	 * @return redirect to livro.xhtml
	 */
	public String efetuarLogin() {

		if (usuarioExiste(usuario)) {
//			System.out.println("Efetuando Login do Usuário " + usuario.getEmail());

			context	
					.getExternalContext()
				   	.getSessionMap()
					.put("usuarioLogado", this.usuario);

			return "livro?faces-redirect=true";

		}

		Flash flash = context.getExternalContext().getFlash();
		flash.setKeepMessages(true);

		context.addMessage(null, new FacesMessage(SEVERITY_ERROR, "Erro ao efetuar login, usuário ou senha incorretos.", null));
//		System.err.println("Erro ao efetuar login, usuário ou senha incorretos.");
		return "login?faces-redirect=true";

	}

	/**
	 * Desloga o usuário e o redireciona para a página de login.
	 * 
	 * @return Página de Login
	 */
	public String deslogar() {
		context.getExternalContext().getSessionMap().remove("usuarioLogado");
		return "login?faces-redirect=true";
	}

	private boolean usuarioExiste(Usuario usuario) {
		return usuarioDAO.usuarioExiste(usuario);
	}

}
