package br.com.alunos.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.alunos.entity.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long>{

	List<Aluno> findAllByAtivoIsTrue();
	
	Optional<Aluno> findFirstByIdAndAtivoIsTrue(Long id);
	
	List<Aluno> findAllBySerie(Integer serie);
	
	Aluno findFirstByNome(String nome);
	
}
