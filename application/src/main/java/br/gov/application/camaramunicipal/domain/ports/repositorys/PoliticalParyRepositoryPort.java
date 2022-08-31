package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.util.List;

import org.springframework.data.domain.Page;

import br.gov.application.camaramunicipal.domain.PoliticalPary;

public interface PoliticalParyRepositoryPort {
    List<PoliticalPary> findAll();

    List<PoliticalPary> findAllLimit(int limit);

    Page<PoliticalPary> findAll(int offSet, int pageSize);

    PoliticalPary findById(Long id);

    PoliticalPary save(PoliticalPary politicalPary);

    void detele(PoliticalPary politicalPary);
}