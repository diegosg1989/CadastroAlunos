package br.com.alunos.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import br.com.alunos.dto.AlunoCreateUpdateDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
public class AlunosVO implements Serializable{

	private static final long serialVersionUID = 7221083961927105464L;

	private List<AlunoCreateUpdateDTO> alunos = new ArrayList<>();
}
