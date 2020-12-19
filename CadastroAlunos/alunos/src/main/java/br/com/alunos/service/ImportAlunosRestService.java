package br.com.alunos.service;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.alunos.dto.AlunoCreateUpdateDTO;
import br.com.alunos.dto.AlunoDTO;
import br.com.alunos.vo.AlunosVO;

@Service
public class ImportAlunosRestService {

	private final RestTemplate restTemplate;
	private final AlunoService alunoService;
	
	public ImportAlunosRestService(RestTemplateBuilder restTemplateBuilder, AlunoService alunoService) {
		this.restTemplate = restTemplateBuilder.build();
		this.alunoService = alunoService;
	}

	public List<AlunoDTO>  importAlunosRest() {
		
		String url = "https://my-json-server.typicode.com/diegosg1989/ApiAlunosTeste/db";
		
		AlunosVO alunosVO = restTemplate.getForObject(url, AlunosVO.class);
		
		List<AlunoCreateUpdateDTO> alunoCreateUpdateDTO = alunosVO.getAlunos();
		
		alunoCreateUpdateDTO.forEach(aluno -> alunoService.create(aluno));
		
		return alunoService.findAll();
	}

}
