package br.newtonpaiva.av1.domain.repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import br.newtonpaiva.av1.domain.entity.Livro;

@Repository
public interface LivroRepository extends MongoRepository<Livro, String>{

	public List<Livro> findByAutor(String autor);
	
}
