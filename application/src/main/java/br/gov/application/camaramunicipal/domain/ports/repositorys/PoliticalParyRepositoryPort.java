package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.application.camaramunicipal.domain.PoliticalPary;

public interface PoliticalParyRepositoryPort {
    List<PoliticalPary> findAll();

    List<PoliticalPary> findAllWithFilters(String fields);

    Page<PoliticalPary> findAllWithFilters(String fields, Pageable pageable);

    PoliticalPary findById(Long id);

    PoliticalPary save(PoliticalPary politicalPary);

    void detele(PoliticalPary politicalPary);
}