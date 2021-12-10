package br.gov.application.camaramunicipal.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.application.camaramunicipal.models.PartidoModel;

public interface PartidoRepository extends JpaRepository<PartidoModel, Long>
{
    Optional<PartidoModel> findByNome(String nome);
    Optional<PartidoModel> findBySigla(String sigla);
}
