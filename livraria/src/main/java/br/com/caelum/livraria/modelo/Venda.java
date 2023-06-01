package br.com.caelum.livraria.modelo;

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id")
public class Venda {

	@Id	@GeneratedValue(strategy = IDENTITY)
	private Integer id;
	
	@ManyToOne
	private Livro livro;
	private Integer quantidade;
	
}
