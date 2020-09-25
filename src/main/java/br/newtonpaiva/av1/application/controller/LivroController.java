package br.newtonpaiva.av1.application.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RestController;

import br.newtonpaiva.av1.api.livros.request.LivroRequest;
import br.newtonpaiva.av1.api.livros.resource.LivroResource;
import br.newtonpaiva.av1.api.livros.response.LivroResponse;
import br.newtonpaiva.av1.common.exception.NotFoundException;
import br.newtonpaiva.av1.domain.entity.Livro;
import br.newtonpaiva.av1.domain.services.LivroService;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@Validated
public class LivroController implements LivroResource{

	@Autowired
	private LivroService livroService;
	
	@Override
	public ResponseEntity<List<LivroResponse>> getAll() {
		log.info("GET livros");
		
		// recupero a lista persistida
		List<Livro> lista = livroService.getLivro();
		
		// preparando o corpo da minha respota REST/JSON
		List<LivroResponse> response = new ArrayList<LivroResponse>();
		
		// para cada livro que eu tenho, coverter de livro para livro-response
		lista.forEach(livro -> {
			response.add(new LivroResponse(livro));
		});
		
		// devolvendo a resposta
		return ResponseEntity.ok(response);
	}

	@Override
	public ResponseEntity<LivroResponse> getById(Optional<String> id) {
		log.info("GET livro by ID: {}", id);

		Livro livro = livroService.getById(id.get());
		
		if (livro == null) {
			// retornar HTTP 404
			throw new NotFoundException(id.get());
		}
		
		return ResponseEntity.ok(new LivroResponse(livro));
	}

	@Override
	public ResponseEntity<LivroResponse> create(Optional<LivroRequest> request) {
		log.info("POST criar livro: {}", request);
		
		log.info(request.get().toString());

		
		// criando um obj livro a partir da requisição JSON
		Livro livro = new Livro()
				.withId(UUID.randomUUID().toString())
				.withTitulo(request.get().getTitulo())
				.withAutor(request.get().getAutor())
				.withEditora(request.get().getEditora())
				.withDataDePublicacao(request.get().getDataDePublicacao())
				.withStatus(request.get().getStatus());
		
		livro.setCreated(LocalDateTime.now());
		livro.setModified(LocalDateTime.now());
		
		// salvar em memória
		livro = livroService.create(livro);
		
		// devolver a resposta do livro criado
		return ResponseEntity.status(HttpStatus.CREATED).body(new LivroResponse(livro));
	}

	@Override
	public ResponseEntity<LivroResponse> update(
			Optional<String> id, 
			Optional<LivroRequest> request
			) {
		log.info("PUT atualizar livro: {} {}", id, request);

		Livro livro = new Livro()
				.withTitulo(request.get().getTitulo())
				.withAutor(request.get().getAutor())
				.withEditora(request.get().getEditora())
				.withDataDePublicacao(request.get().getDataDePublicacao())
				.withStatus(request.get().getStatus());


		// salvar em memória
		livro = livroService.update(id.get(), livro);
		
		// devolver a resposta do livro criado
		return ResponseEntity.status(HttpStatus.OK).body(new LivroResponse(livro));
	}

	@Override
	public ResponseEntity<Void> deleteById(Optional<String> id) {
		log.info("DELETE remover livro: {}", id);

		livroService.deleteById(id.get());
		
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

}

