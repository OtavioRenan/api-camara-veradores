package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.util.List;

import br.gov.application.camaramunicipal.domain.Parliamentary;

public interface ParliamentaryRepositoryPort {
    List<Parliamentary> findAll();

    Parliamentary findById(Long id);

    Parliamentary save(Parliamentary parliamentary);

    void deteleById(Long id);
}
