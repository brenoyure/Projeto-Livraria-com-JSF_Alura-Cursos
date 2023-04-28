package br.com.caelum.livraria.bean;

import java.io.Serializable;

import javax.faces.view.ViewScoped;
import javax.inject.Named;

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
	 * Necessário para que o Bean funcione, com o escopo maior que RequestScope.
	 */
	private static final long serialVersionUID = 1L;

	@Getter
	private Usuario usuario = new Usuario();

	public String efetuarLogin() {
		System.out.println("Efetuando Login do Usuário " + usuario.getEmail());
		return "livro?faces-redirect=true";
	}

}
