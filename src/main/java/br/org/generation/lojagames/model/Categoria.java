package br.org.generation.lojagames.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

// Definir que é uma classe do tipo model que irá definir uma tabela
@Entity
// Alterar o nome da tabela
@Table(name = "tb_categoria")
public class Categoria {

	// Definir que o atributo Id será uma chave primária
	@Id
	// Definir o auto incremento 1, 2, 3... (a chave não é natural porque foi criada por nós, não é igual a um CPF)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long Id;
	
	// O campo não pode ser nulo
	@NotNull(message = "O atributo tipo não pode ser nulo!")
	private String tipo;
	
	@OneToMany(mappedBy = "tb_categoria", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("tb_categoria")
	// Cria uma lista de postagens
	private List<Produto> tb_produtos;

	public long getId() {
		return Id;
	}

	public void setId(long id) {
		Id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public List<Produto> getTb_produtos() {
		return tb_produtos;
	}

	public void setTb_produtos(List<Produto> tb_produtos) {
		this.tb_produtos = tb_produtos;
	}
	
}
