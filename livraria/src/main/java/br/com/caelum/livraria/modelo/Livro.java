package br.com.caelum.livraria.modelo;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
public class Livro {

	@Id
	@GeneratedValue(strategy = IDENTITY)
	private Integer id;

	private String titulo;
	private String isbn;
	private double preco;
	private String dataLancamento;

	@ManyToMany
	private List<Autor> autores = new ArrayList<>();

	public void adicionaAutor(Autor autor) {
		this.autores.add(autor);
	}

}