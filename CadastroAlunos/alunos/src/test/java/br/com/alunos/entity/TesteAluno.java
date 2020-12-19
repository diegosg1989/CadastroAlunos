package br.com.alunos.entity;

import static org.junit.Assert.assertEquals;

import org.junit.jupiter.api.Test;

import nl.jqno.equalsverifier.EqualsVerifier;

public class TesteAluno {

    @Test
    public void testGettersAndSetters() {
    	
    	Long id = 1L;
    	String nome = "Diego Guerra";
    	int idade = 31;
    	String serie = "5 serie";
    	
    	Aluno aluno = new Aluno(id, nome, idade, serie, true);
    	
    	assertEquals(id, aluno.getId());
    	assertEquals(nome, aluno.getNome());
    	assertEquals(idade, aluno.getIdade());
    	assertEquals(serie, aluno.getSerie());
    	assertEquals(true, aluno.getAtivo());
    	assertEquals(null, aluno.getDataAtualizacao());
    }
    
    @Test
    public void testaEqualsAndVerifier(){
    	EqualsVerifier.forClass(Aluno.class).verify();
    }
}
