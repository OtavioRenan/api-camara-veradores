package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.application.camaramunicipal.domain.DirectorTable;

public interface DirectorTableRepositoryPort {
    List<DirectorTable> findAll();

    List<DirectorTable> findAllWithFilters(Long legislatureId, Long adjutancyId, Long parliamentaryId);

    Page<DirectorTable> findAllWithFilters(Long legislatureId, Long adjutancyId, Long parliamentaryId, Pageable pageable);

    DirectorTable findById(Long id);

    DirectorTable save(DirectorTable directorTable);

    void detele(DirectorTable directorTable);
}
