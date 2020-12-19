package br.com.alunos.service;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.web.client.RestTemplate;

import br.com.alunos.dto.AlunoCreateUpdateDTO;
import br.com.alunos.dto.AlunoDTO;
import br.com.alunos.vo.AlunosVO;

public class TesteImportAlunosRestService {

	@Test
	public void testaImportAlunosRestService() {
		
		RestTemplateBuilder restTemplateBuilder = Mockito.mock(RestTemplateBuilder.class);
		AlunoService alunoService = Mockito.mock(AlunoService.class);
		RestTemplate restTemplate = Mockito.mock(RestTemplate.class);
		
		Mockito.when(restTemplateBuilder.build()).thenReturn(restTemplate);
		
		ImportAlunosRestService importAlunosRestService = Mockito.spy(new ImportAlunosRestService(restTemplateBuilder, alunoService));
		
		AlunoCreateUpdateDTO alunoCreateUpdateDTO1 = new AlunoCreateUpdateDTO("Aluno 1", 7, "1 serie");
		AlunoCreateUpdateDTO alunoCreateUpdateDTO2 = new AlunoCreateUpdateDTO("Aluno 2", 8, "2 serie");
		List<AlunoCreateUpdateDTO> alunoCreateUpdateDTO = Lists.newArrayList(alunoCreateUpdateDTO1, alunoCreateUpdateDTO2);
		
		AlunosVO alunoVO = new AlunosVO(alunoCreateUpdateDTO);
		
		Mockito.when(restTemplate.getForObject(Mockito.anyString(), Mockito.any())).thenReturn(alunoVO);
		
		AlunoDTO alunoDTO1 = new AlunoDTO(1L, "Aluno 1", 7, "1 serie", true);
		AlunoDTO alunoDTO2 = new AlunoDTO(2L, "Aluno 2", 8, "2 serie", true);
		List<AlunoDTO> alunosEsperados = Lists.newArrayList(alunoDTO1, alunoDTO2);
		
		Mockito.when(alunoService.findAll()).thenReturn(alunosEsperados);
		
		//metodo a ser testado
		List<AlunoDTO> alunosRetornados = importAlunosRestService.importAlunosRest();
		
		Mockito.verify(alunoService).create(alunoCreateUpdateDTO1);
		Mockito.verify(alunoService).create(alunoCreateUpdateDTO2);
		assertEquals(alunosEsperados, alunosRetornados);
	}
}
