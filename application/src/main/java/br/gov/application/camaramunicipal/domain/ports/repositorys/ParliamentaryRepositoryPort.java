package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.application.camaramunicipal.domain.Parliamentary;

public interface ParliamentaryRepositoryPort {
    List<Parliamentary> findAll();

    List<Parliamentary> findAllWithFilters(Long politicalParyId, Long legislatureId, Date birth, String fields);

    Page<Parliamentary> findAllWithFilters(Long politicalParyId, Long legislatureId, Date birth, String fields, Pageable pageable);

    Parliamentary findById(Long id);

    Parliamentary save(Parliamentary parliamentary);

    void detele(Parliamentary parliamentary);
}
