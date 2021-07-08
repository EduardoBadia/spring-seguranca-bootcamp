package br.com.zup.bootcamp.controller.dto;

import org.springframework.data.domain.Page;

import br.com.zup.bootcamp.modelo.Aluno;

public class AlunoDto {

	private Long id;
	private String nome;
	private String email;
	private Integer idade;
	
	public AlunoDto(Aluno aluno) {
		
		this.id = aluno.getId();
		this.nome = aluno.getNome();
		this.email = aluno.getEmail();
		this.idade = aluno.getIdade();
	}

	public Long getId() {
		return id;
	}

	public String getNome() {
		return nome;
	}

	public String getEmail() {
		return email;
	}

	public Integer getIdade() {
		return idade;
	}

	public static Page<AlunoDto> converter(Page<Aluno> alunos) {
		return alunos.map(AlunoDto::new);
	}

}
