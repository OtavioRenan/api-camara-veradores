package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.util.List;

import br.gov.application.camaramunicipal.domain.Adjutancy;

public interface AdjutancyRepositoryPort {
    List<Adjutancy> findAll();

    Adjutancy findById(Long id);

    Adjutancy save(Adjutancy adjutancy);

    void deteleById(Long id);
}
