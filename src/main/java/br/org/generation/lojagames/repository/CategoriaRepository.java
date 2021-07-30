package br.org.generation.lojagames.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.org.generation.lojagames.model.Categoria;

// Apenas assina o método (determina o que ele vai fazer) e essa interface define os métodos que irão consultar o banco de dados
									// classe que vai utilizar os métodos e o objeto do tipo Long (id) que é o parâmetro chave primária
@Repository
public interface CategoriaRepository extends JpaRepository<Categoria, Long> {
	
	public List<Categoria> findAllByTipoContainingIgnoreCase(String tipo);
}
