package br.com.zup.bootcamp.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.zup.bootcamp.controller.dto.AlunoDto;
import br.com.zup.bootcamp.controller.form.AlunoForm;
import br.com.zup.bootcamp.controller.form.AtualizacaoAlunoForm;
import br.com.zup.bootcamp.modelo.Aluno;
import br.com.zup.bootcamp.repository.AlunoRepository;
import br.com.zup.bootcamp.repository.AvaliacaoRepository;

@RestController
@RequestMapping("/alunos")
public class AlunoController {

	@Autowired
	private AlunoRepository alunoRepository;

	@Autowired
	private AvaliacaoRepository avaliacaoRepository;

	@GetMapping
	@Cacheable(value = "listaDeAlunos")
	public Page<AlunoDto> lista(
			@PageableDefault(sort = "nome", direction = Direction.DESC, page = 0, size = 10) Pageable paginacao) {

		Page<Aluno> alunos = alunoRepository.findAll(paginacao);
		return AlunoDto.converter(alunos);
	}

	@PostMapping
	@Transactional
	@CacheEvict(value = "listaDeAlunos", allEntries = true)
	public ResponseEntity<AlunoDto> cadastrar(@RequestBody @Valid AlunoForm form, UriComponentsBuilder uriBuilder) {

		Aluno aluno = form.converter(avaliacaoRepository);
		alunoRepository.save(aluno);

		URI uri = uriBuilder.path("/alunos/{id}").buildAndExpand(aluno.getId()).toUri();
		return ResponseEntity.created(uri).body(new AlunoDto(aluno));
	}

	@PutMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeAlunos", allEntries = true)
	public ResponseEntity<AlunoDto> atualizar(@PathVariable Long id, @RequestBody @Valid AtualizacaoAlunoForm form) {
		Optional<Aluno> optional = alunoRepository.findById(id);
		if (optional.isPresent()) {
			Aluno aluno = form.atualizar(id, alunoRepository);
			return ResponseEntity.ok(new AlunoDto(aluno));
		}

		return ResponseEntity.notFound().build();
	}

	@DeleteMapping("/{id}")
	@Transactional
	@CacheEvict(value = "listaDeAlunos", allEntries = true)
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Aluno> optional = alunoRepository.findById(id);
		if (optional.isPresent()) {
			alunoRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
