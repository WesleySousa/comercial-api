package br.com.algaworks.comercial.resource;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.algaworks.comercial.exception.OportunidadeJaExisteComMesmoProspectoEDescricaoException;
import br.com.algaworks.comercial.exception.OportunidadeNaoExisteException;
import br.com.algaworks.comercial.model.Oportunidade;
import br.com.algaworks.comercial.repository.OportunidadeRepository;
import br.com.algaworks.comercial.service.OportunidadeService;

@RestController
@RequestMapping("/oportunidades")
public class OportunidadeResource {

	@Autowired
	private OportunidadeRepository oportunidadeRepository;
	@Autowired
	private OportunidadeService oportunidadeService;

	@GetMapping
	public List<Oportunidade> listar() {
		return oportunidadeRepository.findAll();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Oportunidade> buscar(@PathVariable Long id) {
		Optional<Oportunidade> oportunidade = oportunidadeRepository.findById(id);

		if (oportunidade.isPresent()) {
			return ResponseEntity.ok(oportunidade.get());
		}

		return ResponseEntity.notFound().build();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Oportunidade adicionar(@Valid @RequestBody Oportunidade oportunidade) {
		try {
			return oportunidadeService.salvar(oportunidade);
		} catch (OportunidadeJaExisteComMesmoProspectoEDescricaoException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		}
	}

	@PutMapping
	public Oportunidade editar(@Valid @RequestBody Oportunidade oportunidade) {
		try {
			return oportunidadeService.editar(oportunidade);
		} catch (OportunidadeJaExisteComMesmoProspectoEDescricaoException | IllegalArgumentException e) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
		} catch (OportunidadeNaoExisteException e) {
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, e.getMessage());
		}
	}
}
