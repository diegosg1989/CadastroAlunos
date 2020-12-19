package br.com.alunos.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "TB_ALUNOS")
@Data
@EqualsAndHashCode
public class Aluno {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	@EqualsAndHashCode.Exclude
	private Long id;
	
	@Column
	private String nome;
	
	@Column
	private int idade;
	
	@Column
	private String serie;
	
	@Column
	private Boolean ativo = true;
	
	@CreatedDate
    @Column(name = "data_criacao")
	@EqualsAndHashCode.Exclude
    private Date dataCriacao = new Date();

    @LastModifiedDate
    @Column(name = "data_atualizacao")
    @EqualsAndHashCode.Exclude
    private Date dataAtualizacao;

    public Aluno() {}
    
	public Aluno(Long id, String nome, int idade, String serie, Boolean ativo) {
		this.id = id;
		this.nome = nome;
		this.idade = idade;
		this.serie = serie;
		this.ativo = ativo;
	}
}
