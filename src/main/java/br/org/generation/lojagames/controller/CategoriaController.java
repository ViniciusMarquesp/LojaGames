package br.org.generation.lojagames.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.org.generation.lojagames.model.Categoria;
import br.org.generation.lojagames.repository.CategoriaRepository;

@RestController
// Mapear o end-point
// Sempre minusculo, pois não existe endereço de site com letra maiuscula
@RequestMapping("/categorias")
// Para o front entender que irá consumir a classe
@CrossOrigin(origins="*", allowedHeaders="*")
public class CategoriaController {

	// Injetar o objeto categoriaRepository e transferir a responsabilidade de instanciar o objeto e usá-lo para o Spring
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@GetMapping
			// Vai gerar uma resposta HTTP, retornando um objeto do tipo Categoria
						  // Precisa usar um list porque pode retornar mais de uma resposta
	private ResponseEntity<List<Categoria>> getAll() {
		// A resposta será do tipo ok, puxando os dados a partir da repository
								// Puxando a resposta de JPA
		return ResponseEntity.ok(categoriaRepository.findAll());
	}
	
	// Função Lambda
	@GetMapping("/{id}")
	private ResponseEntity<Categoria> getById(@PathVariable long id) {
		return categoriaRepository.findById(id)
				.map(resp -> ResponseEntity.ok(resp))
				.orElse(ResponseEntity.notFound().build());
	}
	
	@GetMapping("/tipo/{tipo}")
	public ResponseEntity<List<Categoria>> getByTipo(@PathVariable String tipo) {
		return ResponseEntity.ok(categoriaRepository.findAllByTipoContainingIgnoreCase(tipo));
	}
	
	@PostMapping
	public ResponseEntity<Categoria> post(@RequestBody Categoria categoria) {
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(categoriaRepository.save(categoria));
	}
	
	@PutMapping
	public ResponseEntity<Categoria> put(@RequestBody Categoria categoria) {
							// Retorna um ok	
		return ResponseEntity.ok(categoriaRepository.save(categoria));			
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable long id) {
		categoriaRepository.deleteById(id);
	}
	
}
