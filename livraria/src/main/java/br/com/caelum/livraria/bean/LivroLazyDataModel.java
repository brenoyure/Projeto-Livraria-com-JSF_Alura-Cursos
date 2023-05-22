package br.com.caelum.livraria.bean;

import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;

import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;

import br.com.caelum.livraria.dao.LivroDAO;
import br.com.caelum.livraria.modelo.Livro;

@Stateless
public class LivroLazyDataModel extends LazyDataModel<Livro> {

	private static final long serialVersionUID = 1L;

	@Inject
	private LivroDAO dao;

	@Override
	public List<Livro> load(int first, int pageSize, Map<String, SortMeta> sortBy, Map<String, FilterMeta> filtros) {

		if (filtros != null) {
			for (FilterMeta meta : filtros.values()) {

//				String filterField = meta.getField();
//				Object filterValue = meta.getFilterValue();

//				if (filterField.equalsIgnoreCase("titulo") && filterValue != null) {
					return dao.listaTodosPaginada(first, pageSize, meta.getField(), meta.getFilterValue().toString());
//				}

			}
		}

		return dao.listaTodosPaginada(first, pageSize, null, null);
	}

	@Override
	public int count(Map<String, FilterMeta> filterBy) {
		return dao.quantidadeDeElementos();
	}

}
