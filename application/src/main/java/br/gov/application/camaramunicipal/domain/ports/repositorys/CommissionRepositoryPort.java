package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.application.camaramunicipal.domain.Commission;

public interface CommissionRepositoryPort {
    List<Commission> findAll();

    List<Commission> findAllWithFilters(String fields);

    Page<Commission> findAllWithFilters(String fields, Pageable pageable);

    Commission findById(Long id);

    Commission save(Commission commission);

    void detele(Commission commission);
}