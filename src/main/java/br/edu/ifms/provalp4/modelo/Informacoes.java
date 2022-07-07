package br.edu.ifms.provalp4.modelo;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotEmpty;

@Entity (name="informacoes")
public class Informacoes {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotEmpty(message = "O preço deve ser informado")
	private String preco;
	
	@NotEmpty(message = "O setor deve ser informado")
	private String setor;

	
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idproduto")
	private Produto produto;

	public Long getId() {
		return id;
	}

	
	public void setId(Long id) {
		this.id = id;
	}

	
	public String getPreco() {
		return preco;
	}

	
	public void setPreco(String preco) {
		this.preco = preco;
	}

	
	public String getSetor() {
		return setor;
	}

	
	public void setSetor(String setor) {
		this.setor = setor;
	}

	
	public Produto getProduto() {
		return produto;
	}

	
	public void setProduto(Produto produto) {
		this.produto = produto;
	}	
	
}
