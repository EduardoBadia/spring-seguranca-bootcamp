package br.com.zup.bootcamp.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import br.com.zup.bootcamp.modelo.Aluno;
import br.com.zup.bootcamp.modelo.Avaliacao;
import br.com.zup.bootcamp.repository.AvaliacaoRepository;

public class AlunoForm {

	@NotBlank 
	private String nome;
	
	@NotBlank
	@Email
	private String email;
	
	@NotNull
	@Min(value = 18)
	@Max(value = 150)
	private Integer idade;
	
	private String titulo;

	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public Aluno converter(AvaliacaoRepository ar)
	{
		Avaliacao avaliacao = ar.findByTitulo(titulo);
		return new Aluno(nome, email, idade, avaliacao);
	}
}
