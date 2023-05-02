package br.com.caelum.livraria.util;

import static javax.faces.event.PhaseId.RESTORE_VIEW;

import javax.faces.application.NavigationHandler;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

import br.com.caelum.livraria.modelo.Usuario;

public class LoginPhaseListener implements PhaseListener {

	private static final long serialVersionUID = 1L;

	@Override
	public void afterPhase(PhaseEvent event) {
		FacesContext context = event.getFacesContext();
		String nomePagina = context.getViewRoot().getViewId();

		System.out.println(nomePagina);

		if (nomePagina.equals("/login.xhtml")) {
			return;
		}

		Usuario usuarioLogado = (Usuario) context.getExternalContext()
													.getSessionMap()
													.get("usuarioLogado");

		if (usuarioLogado != null) {
			return;
		}

		NavigationHandler navHandler = context.getApplication().getNavigationHandler();
		navHandler.handleNavigation(context, null, "login?faces-redirect=true");
		context.renderResponse();

	}

	@Override
	public void beforePhase(PhaseEvent event) {
		// TODO Auto-generated method stub

	}

	@Override
	public PhaseId getPhaseId() {
		return RESTORE_VIEW;
	}

}
