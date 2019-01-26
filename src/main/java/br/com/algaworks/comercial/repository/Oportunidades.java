package br.com.algaworks.comercial.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.algaworks.comercial.model.Oportunidade;

public interface Oportunidades extends JpaRepository<Oportunidade, Long>{

	public Optional<Oportunidade> findByDescricaoAndNomeProspecto(String descricao, String nomeProspecto);
}
