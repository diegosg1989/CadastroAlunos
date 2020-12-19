package br.com.alunos.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.alunos.dto.AlunoCreateUpdateDTO;
import br.com.alunos.dto.AlunoDTO;
import br.com.alunos.dto.AlunoUpdateSerieDTO;
import br.com.alunos.service.AlunoService;
import br.com.alunos.service.ImportAlunosRestService;
import io.swagger.v3.oas.annotations.Operation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping(value = "v1/alunos")
public class AlunoController {

	private final AlunoService alunoService;
	
	private final ImportAlunosRestService importAlunosRestService;
	
	public AlunoController(AlunoService alunoService, ImportAlunosRestService importAlunosRestService) {
		this.alunoService = alunoService;
		this.importAlunosRestService = importAlunosRestService;
	}
	
	@GetMapping
	@Operation(description = "Relação de alunos cadastrados")
	public List<AlunoDTO> listAll(){
		return alunoService.findAll();
	}
	
	@GetMapping("{id}")
	@Operation(description = "Busca um aluno a partir de seu id")
	public AlunoDTO getById(@PathVariable Long id) {
		return alunoService.findById(id);
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	@Operation(description = "Cria um novo aluno")
	public AlunoDTO create(@RequestBody AlunoCreateUpdateDTO alunoCreateUpdateDTO) {
		return alunoService.create(alunoCreateUpdateDTO);
	}
	
	@PutMapping("{id}")
	@Operation(description = "Atualiza dados de um aluno")
	public AlunoDTO update(@PathVariable Long id, @RequestBody AlunoCreateUpdateDTO alunoCreateUpdateDTO) {
		return alunoService.update(id, alunoCreateUpdateDTO);
	}
	
	@PatchMapping("{id}/serie")
	@Operation(description = "Atualiza a serie de um aluno")
	public AlunoDTO updateSerie(@PathVariable Long id, @RequestBody AlunoUpdateSerieDTO alunoUpdateSerieDTO) {
		return alunoService.updateSerie(id, alunoUpdateSerieDTO);
	}
	
	@DeleteMapping("{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Long id) {
		alunoService.delete(id);
	}
	
	@GetMapping("/import")
	@Operation(description = "Importa de uma api os alunos")
	public List<AlunoDTO> importAll(){
		return importAlunosRestService.importAlunosRest();
	}
}
