package br.com.algaworks.comercial.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.algaworks.comercial.exception.OportunidadeJaExisteComMesmoProspectoEDescricaoException;
import br.com.algaworks.comercial.exception.OportunidadeNaoExisteException;
import br.com.algaworks.comercial.model.Oportunidade;
import br.com.algaworks.comercial.repository.OportunidadeRepository;

@Service
public class OportunidadeService {

	@Autowired
	private OportunidadeRepository oportunidadeRepository;

	public Oportunidade salvar(Oportunidade oportunidade)
			throws OportunidadeJaExisteComMesmoProspectoEDescricaoException {

		Optional<Oportunidade> oportunidadeExistente = oportunidadeRepository
				.findByDescricaoAndNomeProspecto(oportunidade.getDescricao(), oportunidade.getNomeProspecto());

		if (oportunidadeExistente.isPresent()) {
			throw new OportunidadeJaExisteComMesmoProspectoEDescricaoException();
		}

		return oportunidadeRepository.save(oportunidade);
	}

	public Oportunidade editar(Oportunidade oportunidade)
			throws OportunidadeJaExisteComMesmoProspectoEDescricaoException, OportunidadeNaoExisteException {

		if (oportunidade.getId() == null) {
			throw new IllegalArgumentException("Id é obrigatório");
		}
		Optional<Oportunidade> oportunidadeExistente = oportunidadeRepository.findById(oportunidade.getId());

		if (oportunidadeExistente.isPresent()) {
			oportunidadeExistente = oportunidadeRepository.findByDescricaoAndNomeProspecto(oportunidade.getDescricao(),
					oportunidade.getNomeProspecto());

			if (oportunidadeExistente.isPresent() && !oportunidadeExistente.get().equals(oportunidade)) {
				throw new OportunidadeJaExisteComMesmoProspectoEDescricaoException();
			} else {
				return oportunidadeRepository.save(oportunidade);
			}
		}

		throw new OportunidadeNaoExisteException();

	}
}
