package br.edu.ifms.provalp4.modelo;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Produto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, message = "O nome deve ter no mínimo 3 carateres")
	private String nome;
	
	@NotEmpty(message = "O tipo do produto deve ser informado")
	private String tipo;	
	
	
	@OneToOne(cascade = CascadeType.ALL, mappedBy = "produto", 
			  fetch = FetchType.EAGER)
	@Valid
	private Informacoes informacoes;


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	
	public String getTipo() {
		return tipo;
	}


	public void setTipo(String tipo) {
		this.tipo = tipo;
	}


	public Informacoes getInformacoes() {
		return informacoes;
	}


	public void setInformacoes(Informacoes informacoes) {
		this.informacoes = informacoes;
	}
	
	
	
}
