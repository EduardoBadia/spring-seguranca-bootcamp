package br.com.zup.bootcamp.controller.form;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import br.com.zup.bootcamp.modelo.Aluno;
import br.com.zup.bootcamp.repository.AlunoRepository;

public class AtualizacaoAlunoForm {
	
	@NotBlank @Length(min = 5)
	private String nome;
	
	@NotBlank @Length(min = 10)
	private String email;
	
	@NotNull
	@Min(value = 18)
	@Max(value = 150)
	private Integer idade;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public Aluno atualizar(Long id, AlunoRepository topicoRepository) {
		
		Aluno aluno = topicoRepository.getOne(id);
		
		aluno.setNome(nome);
		aluno.setEmail(email);
		aluno.setIdade(idade);
		
		return aluno;
	}
	
}
