package br.gov.application.camaramunicipal.domain.ports.repositorys;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.gov.application.camaramunicipal.domain.Legislature;

public interface LegislatureRepositoryPort {
    List<Legislature> findAll();

    List<Legislature> findAllWithFilters(String fields, Date dateStart, Date dateEnd);

    Page<Legislature> findAllWithFilters(String fields, Date dateStart, Date dateEnd, Pageable pageable);

    Legislature findById(Long id);

    Legislature save(Legislature legislature);

    void detele(Legislature legislature);
}