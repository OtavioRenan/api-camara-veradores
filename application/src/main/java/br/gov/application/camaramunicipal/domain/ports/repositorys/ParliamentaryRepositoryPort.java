package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.util.List;

import org.springframework.data.domain.Page;

import br.gov.application.camaramunicipal.domain.Parliamentary;

public interface ParliamentaryRepositoryPort {
    List<Parliamentary> findAll();

    List<Parliamentary> findAllLimit(int limit);

    Page<Parliamentary> findAll(int offSet, int pageSize);

    Parliamentary findById(Long id);

    Parliamentary save(Parliamentary parliamentary);

    void detele(Parliamentary parliamentary);
}
