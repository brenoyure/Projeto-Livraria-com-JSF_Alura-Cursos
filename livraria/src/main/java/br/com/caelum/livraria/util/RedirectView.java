package br.com.caelum.livraria.util;

import static java.lang.String.format;

public class RedirectView {

	private String viewName;

	public RedirectView(String viewName) {
		this.viewName = viewName;
	}

	@Override
	public String toString() {
		return format("%s?faces-redirect=true", viewName);
	}

}
