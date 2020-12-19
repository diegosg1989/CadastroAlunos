package br.com.alunos.service;

import java.util.List;

import br.com.alunos.dto.AlunoCreateUpdateDTO;
import br.com.alunos.dto.AlunoDTO;
import br.com.alunos.dto.AlunoUpdateSerieDTO;

public interface AlunoService {
	
	List<AlunoDTO> findAll();
	
	AlunoDTO findById(Long id);
	
	AlunoDTO create(AlunoCreateUpdateDTO alunoCreateUpdateDTO);
	
	AlunoDTO update(Long id, AlunoCreateUpdateDTO alunoCreateUpdateDTO);
	
	void delete(Long id);

	AlunoDTO updateSerie(Long id, AlunoUpdateSerieDTO alunoUpdateSerieDTO);
}
