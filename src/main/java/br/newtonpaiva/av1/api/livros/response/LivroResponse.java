package br.newtonpaiva.av1.api.livros.response;

import java.time.LocalDateTime;

import br.newtonpaiva.av1.common.enums.StatusLivro;
import br.newtonpaiva.av1.domain.entity.Livro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

@AllArgsConstructor
@Data
@With
public class LivroResponse {

	private String id;
	
	private String titulo;
	
	private String autor;
	
	private String editora;
	
	private LocalDateTime dataDePublicacao;
	
	private StatusLivro status;
	
	private LocalDateTime created;
	
	private LocalDateTime modified;
	
	public LivroResponse(Livro livro) {
		this.id = livro.getId();
		this.titulo = livro.getTitulo();
		this.autor = livro.getAutor();
		this.editora = livro.getEditora();
		this.dataDePublicacao = livro.getDataDePublicacao();
		this.status = livro.getStatus();
		this.created = livro.getCreated();
		this.modified = livro.getModified();
	}
}