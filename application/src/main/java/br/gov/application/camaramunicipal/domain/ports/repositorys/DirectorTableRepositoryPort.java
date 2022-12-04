package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.util.List;

import org.springframework.data.domain.Page;

import br.gov.application.camaramunicipal.domain.DirectorTable;

public interface DirectorTableRepositoryPort {
    List<DirectorTable> findAll();

    List<DirectorTable> findAllLimit(int limit);

    List<DirectorTable> findAllWithFilters(Long legislatureId, Long adjutancyId, Long parliamentaryId);

    Page<DirectorTable> findAll(int offSet, int pageSize);

    DirectorTable findById(Long id);

    DirectorTable save(DirectorTable directorTable);

    void detele(DirectorTable directorTable);
}
