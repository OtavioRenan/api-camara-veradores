package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;

import br.gov.application.camaramunicipal.domain.Legislature;

public interface LegislatureRepositoryPort {
    List<Legislature> findAll();

    List<Legislature> findAllLimit(int limit);

    List<Legislature> findAllWithFilters(String fields, Date dateStart, Date dateEnd);

    Page<Legislature> findAll(int offSet, int pageSize);

    Legislature findById(Long id);

    Legislature save(Legislature legislature);

    void detele(Legislature legislature);
}