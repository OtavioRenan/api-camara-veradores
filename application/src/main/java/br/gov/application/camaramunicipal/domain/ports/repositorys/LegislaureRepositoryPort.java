package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.util.List;

import br.gov.application.camaramunicipal.domain.Legislature;

public interface LegislaureRepositoryPort {
    List<Legislature> findAll();

    Legislature findById(Long id);

    Legislature save(Legislature legislature);

    void deteleById(Long id);
}