package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.util.List;

import org.springframework.data.domain.Page;

import br.gov.application.camaramunicipal.domain.Adjutancy;

public interface AdjutancyRepositoryPort {
    List<Adjutancy> findAll();

    List<Adjutancy> findAllLimit(int limit);

    List<Adjutancy> findAllWithFilters(String fields);

    Page<Adjutancy> findAll(int offSet, int pageSize);

    Adjutancy findById(Long id);

    Adjutancy save(Adjutancy adjutancy);

    void detele(Adjutancy adjutancy);
}
