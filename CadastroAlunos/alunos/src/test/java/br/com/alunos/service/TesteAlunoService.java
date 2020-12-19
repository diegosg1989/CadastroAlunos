package br.com.alunos.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import br.com.alunos.dto.AlunoCreateUpdateDTO;
import br.com.alunos.dto.AlunoDTO;
import br.com.alunos.entity.Aluno;
import br.com.alunos.repository.AlunoRepository;

public class TesteAlunoService {

	@Test
	public void testaFindAll() {
		
		AlunoRepository mockAlunoRepository = Mockito.mock(AlunoRepository.class);
		AlunoServiceImpl alunoService = Mockito.spy(new AlunoServiceImpl(mockAlunoRepository));
		
		Aluno aluno1 = new Aluno(1L, "aluno 1", 7, "1 serie", true);
		Aluno aluno2 = new Aluno(2L, "aluno 2", 8, "2 serie", true);
		
		List<Aluno> listaAlunos = Lists.newArrayList(aluno1, aluno2);
		
		Mockito.when(mockAlunoRepository.findAll()).thenReturn(listaAlunos);
		
		//metodo a ser testado
		List<AlunoDTO> alunosRetornados = alunoService.findAll();

		AlunoDTO alunoDTO1 = new AlunoDTO(1L, "aluno 1", 7, "1 serie", true);
		AlunoDTO alunoDTO2 = new AlunoDTO(2L, "aluno 2", 8, "2 serie", true);
		
		List<AlunoDTO> esperada = Lists.newArrayList(alunoDTO1, alunoDTO2);
		
		assertEquals(esperada, alunosRetornados);
	}
	
	@Test
	public void testaFindById() {
		
		AlunoRepository mockAlunoRepository = Mockito.spy(AlunoRepository.class);		
		AlunoServiceImpl alunoService = Mockito.spy(new AlunoServiceImpl(mockAlunoRepository));
		
		Aluno aluno1 = new Aluno(1L, "aluno 1", 7, "1 serie", true);
		
		Mockito.doReturn(aluno1).when(alunoService).getAluno(1L);
		
		//metodo a ser testado
		AlunoDTO alunoDTORetornado = alunoService.findById(1L);
		
		AlunoDTO alunoDTOEsperado = alunoService.criaAlunoDTO(aluno1);
		
		assertEquals(alunoDTOEsperado, alunoDTORetornado);
	}
	
	@Test
	public void testaCreate() {
		
		AlunoRepository mockAlunoRepository = Mockito.spy(AlunoRepository.class);		
		AlunoServiceImpl alunoService = Mockito.spy(new AlunoServiceImpl(mockAlunoRepository));
		
		AlunoCreateUpdateDTO alunoCreateUpdateDTO = new AlunoCreateUpdateDTO("aluno 1", 7, "1 serie");
		
		Aluno aluno = new Aluno();
		aluno.setNome(alunoCreateUpdateDTO.getNome());
		aluno.setIdade(alunoCreateUpdateDTO.getIdade());
		aluno.setSerie(alunoCreateUpdateDTO.getSerie());
		
		Mockito.when(mockAlunoRepository.save(Mockito.any())).thenReturn(aluno);
		
		//metodo a ser testado
		AlunoDTO alunoDTORetornado = alunoService.create(alunoCreateUpdateDTO);
		
		Mockito.verify(mockAlunoRepository).save(aluno);
		
		AlunoDTO alunoDTOEsperado = alunoService.criaAlunoDTO(aluno);
		
		assertEquals(alunoDTOEsperado, alunoDTORetornado);
	}
	
	@Test
	public void testaUpdate() {
		
		AlunoRepository mockAlunoRepository = Mockito.spy(AlunoRepository.class);		
		AlunoServiceImpl alunoService = Mockito.spy(new AlunoServiceImpl(mockAlunoRepository));
		
		Aluno aluno1 = new Aluno(1L, "aluno 1", 7, "1 serie", true);
		
		Mockito.doReturn(aluno1).when(alunoService).getAluno(1L);
		
		AlunoCreateUpdateDTO alunoCreateUpdateDTO = new AlunoCreateUpdateDTO("aluno 1", 8, "2 serie");
		
		Aluno alunoAlterado = new Aluno();
		alunoAlterado.setId(1L);
		alunoAlterado.setNome(alunoCreateUpdateDTO.getNome());
		alunoAlterado.setIdade(alunoCreateUpdateDTO.getIdade());
		alunoAlterado.setSerie(alunoCreateUpdateDTO.getSerie());
		
		Mockito.when(mockAlunoRepository.save(Mockito.any())).thenReturn(alunoAlterado);
		
		//metodo a ser testado
		AlunoDTO alunoDTORetornado = alunoService.update(1L, alunoCreateUpdateDTO);
		
		Mockito.verify(mockAlunoRepository).save(alunoAlterado);
		
		AlunoDTO alunoDTOEsperado = alunoService.findById(1L);
		
		assertEquals(alunoDTOEsperado, alunoDTORetornado);
	}
	
	@Test
	public void testaDelete() {
		
		AlunoRepository mockAlunoRepository = Mockito.spy(AlunoRepository.class);		
		AlunoServiceImpl alunoService = Mockito.spy(new AlunoServiceImpl(mockAlunoRepository));
		
		Aluno aluno1 = new Aluno(1L, "aluno 1", 7, "1 serie", true);
		
		Mockito.doReturn(aluno1).when(alunoService).getAluno(1L);
		
		alunoService.delete(1L);
		
		Mockito.verify(mockAlunoRepository).save(aluno1);
	}
	
}
