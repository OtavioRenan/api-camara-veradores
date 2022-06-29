package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.util.List;

import br.gov.application.camaramunicipal.domain.Commission;

public interface CommissionRepositoryPort {
    List<Commission> findAll();

    Commission findById(Long id);

    Commission save(Commission commission);

    void deteleById(Long id);
}