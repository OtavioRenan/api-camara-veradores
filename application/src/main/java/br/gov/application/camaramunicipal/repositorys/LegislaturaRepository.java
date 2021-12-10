package br.gov.application.camaramunicipal.repositorys;

import java.sql.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.application.camaramunicipal.models.LegislaturaModel;

public interface LegislaturaRepository extends JpaRepository<LegislaturaModel, Long>
{
    Optional<LegislaturaModel> findByDescricao(String descricao);
    Optional<LegislaturaModel> findByDataInicio(Date inicio);
    Optional<LegislaturaModel> findByDataFim(Date fim);
}