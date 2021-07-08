package br.com.zup.bootcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.bootcamp.modelo.Resposta;

public interface RespostaRepository extends JpaRepository<Resposta, Long> {

	 Resposta findByResolucao(String resolucao);
}

