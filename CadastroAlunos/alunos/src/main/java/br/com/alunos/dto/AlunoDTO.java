package br.com.alunos.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AlunoDTO {

	private Long id;
	private String nome;
	private int idade;
	private String serie;
	private Boolean ativo;
}
