package br.com.caelum.livraria.modelo;

import static java.util.Calendar.getInstance;
import static javax.persistence.GenerationType.IDENTITY;
import static javax.persistence.TemporalType.TIMESTAMP;

import java.util.Calendar;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Temporal;

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

	@Temporal(TIMESTAMP)
	private Calendar dataLancamento = getInstance();

	@ManyToMany
	private Set<Autor> autores = new LinkedHashSet<>();

	public void adicionaAutor(Autor autor) {
		this.autores.add(autor);
	}

	public void removerAutor(Autor autor) {
		this.autores.remove(autor);
	}

}