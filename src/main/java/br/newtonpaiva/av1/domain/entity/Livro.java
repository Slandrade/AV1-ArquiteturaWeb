package br.newtonpaiva.av1.domain.entity;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;

import br.newtonpaiva.av1.common.enums.StatusLivro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.With;

@AllArgsConstructor
@Data
@With
@NoArgsConstructor
public class Livro {

	@Id
	private String id;
	
	private String titulo;
	
	private String autor;
	
	private String editora;
	
	private LocalDateTime dataDePublicacao;

	private StatusLivro status;
	
	private LocalDateTime created;
	
	private LocalDateTime modified;
}
