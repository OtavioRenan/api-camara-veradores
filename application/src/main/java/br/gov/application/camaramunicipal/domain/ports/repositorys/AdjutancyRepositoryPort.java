package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.application.camaramunicipal.domain.Adjutancy;

public interface AdjutancyRepositoryPort {
    List<Adjutancy> findAll();

    List<Adjutancy> findAllWithFilters(String fields);

    Page<Adjutancy> findAllWithFilters(String fields, Pageable pageable);

    Adjutancy findById(Long id);

    Adjutancy save(Adjutancy adjutancy);

    void detele(Adjutancy adjutancy);
}
