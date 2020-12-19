package br.com.alunos;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.alunos.dto.AlunoCreateUpdateDTO;
import br.com.alunos.entity.Aluno;
import br.com.alunos.repository.AlunoRepository;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
@TestPropertySource(properties = "spring.datasource.url=jdbc:h2:mem:db;DB_CLOSE_ON_EXIT=FALSE")
class AlunosApplicationTests {

	@Autowired
	private AlunoRepository alunoRepository;
	
	@Autowired
	private MockMvc mockMvc;
	
	@Test
	public void testeListAllNew() throws Exception {
		
		Aluno aluno1 = new Aluno(1L, "aluno 1", 7, "6 serie", true);
		alunoRepository.save(aluno1);
		
		Aluno aluno2 = new Aluno(2L, "aluno 2", 8, "7 serie", true);
		alunoRepository.save(aluno2);
		
		Aluno aluno3 = new Aluno(3L, "aluno 3", 9, "8 serie", false);
		alunoRepository.save(aluno3);
		
		mockMvc.perform( MockMvcRequestBuilders
			      .get("/v1/alunos")
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk())
			      .andExpect(content().json("[{\"id\":1,\"nome\":\"aluno 1\",\"idade\":7,\"serie\":\"6 serie\",\"ativo\":true},{\"id\":2,\"nome\":\"aluno 2\",\"idade\":8,\"serie\":\"7 serie\",\"ativo\":true},{\"id\":3,\"nome\":\"aluno 3\",\"idade\":9,\"serie\":\"8 serie\",\"ativo\":false}]"));
	}

	@Test
	public void testaGtById() throws Exception {
		
		Aluno aluno1 = new Aluno(1L, "aluno 1", 7, "6 serie", true);
		alunoRepository.save(aluno1);
		
		mockMvc.perform( MockMvcRequestBuilders
			      .get("/v1/alunos/{id}", 1)
			      .accept(MediaType.APPLICATION_JSON))
			      .andDo(print())
			      .andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("aluno 1"))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.idade").value(7))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.serie").value("6 serie"));
	}
	
	@Test
	public void testaCreate() throws Exception {
		
		AlunoCreateUpdateDTO alunoCreateUpdateDTO = new AlunoCreateUpdateDTO("aluno 1", 7, "2 serie");
		
		mockMvc.perform( MockMvcRequestBuilders
			      .post("/v1/alunos")
			      .content(asJsonString(alunoCreateUpdateDTO))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isCreated())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1L))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("aluno 1"))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.idade").value(7))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.serie").value("2 serie"));
	}
	
	@Test
	public void testaUpdate() throws Exception {
		
		Aluno aluno1 = new Aluno(1L, "aluno 1", 7, "6 serie", true);
		alunoRepository.save(aluno1);
		
		AlunoCreateUpdateDTO alunoCreateUpdateDTO = new AlunoCreateUpdateDTO("aluno 1", 7, "2 serie");
		
		mockMvc.perform( MockMvcRequestBuilders
			      .put("/v1/alunos/{id}", 1)
			      .content(asJsonString(alunoCreateUpdateDTO))
			      .contentType(MediaType.APPLICATION_JSON)
			      .accept(MediaType.APPLICATION_JSON))
			      .andExpect(status().isOk())
			      .andExpect(MockMvcResultMatchers.jsonPath("$.nome").value("aluno 1"))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.idade").value(7))
			      .andExpect(MockMvcResultMatchers.jsonPath("$.serie").value("2 serie"));
	}
	
	@Test
	public void testaDelete() throws Exception {
		
		Aluno aluno1 = new Aluno(1L, "aluno 1", 7, "6 serie", true);
		alunoRepository.save(aluno1);
		
		mockMvc.perform( MockMvcRequestBuilders.delete("/v1/alunos/{id}", 1) );
	}
	
	public static String asJsonString(final Object obj) {
	    try {
	        return new ObjectMapper().writeValueAsString(obj);
	    } catch (Exception e) {
	        throw new RuntimeException(e);
	    }
	}
}
