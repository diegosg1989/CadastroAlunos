package br.com.alunos.dto;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AlunoCreateUpdateDTO implements Serializable {
	
	private static final long serialVersionUID = -8804247971236922012L;
	
	private String nome;
	private int idade;
	private String serie;
}
