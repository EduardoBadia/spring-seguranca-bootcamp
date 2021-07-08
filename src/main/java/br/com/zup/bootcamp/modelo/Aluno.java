package br.com.zup.bootcamp.modelo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

@Entity
public class Aluno {

	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nome; 
	private String email; 
	private Integer idade;
	
	@OneToMany(cascade=CascadeType.ALL, orphanRemoval=true, mappedBy = "aluno")
	private List<Resposta> respostas = new ArrayList<Resposta>();
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Avaliacao> avaliacoes = new ArrayList<Avaliacao>();

	public Aluno() {}
	
	public Aluno(String nome, String email, Integer idade, Avaliacao avaliacao) {
		super();
		this.nome = nome;
		this.email = email;
		this.idade = idade;
		avaliacoes.add(avaliacao);
	}

	public Long getId() {
		return id;
	}
	
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Integer getIdade() {
		return idade;
	}
	public void setIdade(Integer idade) {
		this.idade = idade;
	}
	
	public List<Resposta> getRespostas() {
		return respostas;
	}
	
	public void adicionaResposta(Resposta resposta)
	{
	    if(resposta != null)
	    {
	    	respostas.add(resposta);
	    }	
	}
	
	public void adicionaAvaliacao(Avaliacao avaliacao)
	{
		if(avaliacao != null)
		{	
			avaliacoes.add(avaliacao);
		}
		
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Aluno other = (Aluno) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
