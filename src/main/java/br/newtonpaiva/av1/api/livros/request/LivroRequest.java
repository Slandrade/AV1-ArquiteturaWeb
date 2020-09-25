package br.newtonpaiva.av1.api.livros.request;



import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.newtonpaiva.av1.common.enums.StatusLivro;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.With;

@AllArgsConstructor
@Data
@With
public class LivroRequest {

	private String id;
	
	@NotBlank
	private String titulo;
	
	@NotBlank
	private String autor;
	
	@NotNull
	private String editora;
	
	private LocalDateTime dataDePublicacao;
	
	@NotNull
	private StatusLivro status;
	
}
