package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.util.List;

import org.springframework.data.domain.Page;

import br.gov.application.camaramunicipal.domain.Commission;

public interface CommissionRepositoryPort {
    List<Commission> findAll();

    List<Commission> findAllLimit(int limit);

    List<Commission> findAllWithFilters(String fields);

    Page<Commission> findAll(int offSet, int pageSize);

    Commission findById(Long id);

    Commission save(Commission commission);

    void detele(Commission commission);
}