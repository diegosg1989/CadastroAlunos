package br.com.alunos.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import br.com.alunos.dto.AlunoCreateUpdateDTO;
import br.com.alunos.dto.AlunoDTO;
import br.com.alunos.dto.AlunoUpdateSerieDTO;
import br.com.alunos.entity.Aluno;
import br.com.alunos.repository.AlunoRepository;

@Service
public class AlunoServiceImpl implements AlunoService{
	
	private final AlunoRepository alunoRepository;

	public AlunoServiceImpl(AlunoRepository alunoRepository) {
		this.alunoRepository = alunoRepository;
	}
	
	@Override
	public List<AlunoDTO> findAll(){
		return alunoRepository.findAllByAtivoIsTrue()
				.stream()
				.map(aluno -> criaAlunoDTO(aluno))
				.collect(Collectors.toList());
	}
	
	@Override
	public AlunoDTO findById(Long id) {
		Aluno aluno = getAluno(id);
		return criaAlunoDTO(aluno);
	}
	
	@Override
	public AlunoDTO create(AlunoCreateUpdateDTO alunoCreateUpdateDTO) {
		
		Aluno aluno = new Aluno();
		aluno.setNome(alunoCreateUpdateDTO.getNome());
		aluno.setIdade(alunoCreateUpdateDTO.getIdade());
		aluno.setSerie(alunoCreateUpdateDTO.getSerie());
		
		Aluno alunoSaved = alunoRepository.save(aluno);
		
		return criaAlunoDTO(alunoSaved);
	}
	
	@Override
	public AlunoDTO update(Long id, AlunoCreateUpdateDTO alunoCreateUpdateDTO) {
		
		Aluno aluno = getAluno(id);
		
		aluno.setNome(alunoCreateUpdateDTO.getNome());
		aluno.setIdade(alunoCreateUpdateDTO.getIdade());
		aluno.setSerie(alunoCreateUpdateDTO.getSerie());
		
		Aluno alunoSaved = alunoRepository.save(aluno);
		
		return criaAlunoDTO(alunoSaved);
	}
	
	@Override
	public void delete(Long id) {
		
		Aluno aluno = getAluno(id);
		aluno.setAtivo(false);
		alunoRepository.save(aluno);
	}
	
	@Override
	public AlunoDTO updateSerie(Long id, AlunoUpdateSerieDTO alunoUpdateSerieDTO) {
		
		Aluno aluno = getAluno(id);
		
		aluno.setSerie(alunoUpdateSerieDTO.getSerie());
		
		Aluno alunoSaved = alunoRepository.save(aluno);
		
		return criaAlunoDTO(alunoSaved);
	}
	
    protected Aluno getAluno(Long id) {
        return alunoRepository.findFirstByIdAndAtivoIsTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    
	protected AlunoDTO criaAlunoDTO(Aluno aluno) {
		return new AlunoDTO(
				aluno.getId(), 
				aluno.getNome(), 
				aluno.getIdade(), 
				aluno.getSerie(), 
				aluno.getAtivo());
	}
}
