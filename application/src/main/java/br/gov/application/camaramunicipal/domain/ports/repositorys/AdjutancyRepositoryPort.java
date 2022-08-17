package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.util.List;

import org.springframework.data.domain.Page;

import br.gov.application.camaramunicipal.domain.Adjutancy;

public interface AdjutancyRepositoryPort {
    List<Adjutancy> findAll();

    Page<Adjutancy> findAll(int offSet, int pageSize);    

    Adjutancy findById(Long id);

    Adjutancy save(Adjutancy adjutancy);

    void deteleById(Long id);
}
