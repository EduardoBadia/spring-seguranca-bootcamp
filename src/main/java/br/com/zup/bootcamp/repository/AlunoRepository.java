package br.com.zup.bootcamp.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import br.com.zup.bootcamp.modelo.Aluno;

public interface AlunoRepository extends JpaRepository<Aluno, Long> {

}
