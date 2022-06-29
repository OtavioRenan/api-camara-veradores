package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.util.List;

import br.gov.application.camaramunicipal.domain.PoliticalPary;

public interface PoliticalParyRepositoryPort {
    List<PoliticalPary> findAll();

    PoliticalPary findById(Long id);

    PoliticalPary save(PoliticalPary politicalPary);

    void deteleById(Long id);
}