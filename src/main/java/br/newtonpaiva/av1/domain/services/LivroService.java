package br.newtonpaiva.av1.domain.services;

import java.util.List;

import br.newtonpaiva.av1.domain.entity.Livro;

public interface LivroService {
	
	Livro getById(String id);
	
	List<Livro> getLivro();
	
	Livro create(Livro livro);
	
	Livro update(String id, Livro livro);
	
	void deleteById(String id);

}
