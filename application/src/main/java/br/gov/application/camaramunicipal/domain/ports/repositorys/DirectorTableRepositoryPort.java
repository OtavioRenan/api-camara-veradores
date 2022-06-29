package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.util.List;

import br.gov.application.camaramunicipal.domain.DirectorTable;

public interface DirectorTableRepositoryPort {
    List<DirectorTable> findAll();

    DirectorTable findById(Long id);

    DirectorTable save(DirectorTable directorTable);

    void deteleById(Long id);
}
