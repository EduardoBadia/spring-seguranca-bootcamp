package br.com.zup.bootcamp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.bootcamp.modelo.Avaliacao;
import br.com.zup.bootcamp.modelo.Resposta;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {

	Avaliacao findByTitulo(String titulo);
}
