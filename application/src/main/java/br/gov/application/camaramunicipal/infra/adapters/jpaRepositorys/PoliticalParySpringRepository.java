package br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.application.camaramunicipal.infra.adapters.entitys.PoliticalParyEntity;

public interface PoliticalParySpringRepository extends JpaRepository<PoliticalParyEntity, Long> {
    @Query(value = "SELECT * FROM politicals_parys " +
    "WHERE ( (:fields IS NULL OR :fields = '') OR name LIKE %" + ":fields" + "% OR initials LIKE %" + ":fields" + "% )"
    , nativeQuery = true)
    List<PoliticalParyEntity> findAllWithFilters(String fields);

    @Query(value = "SELECT * FROM politicals_parys " +
    "WHERE ( (:fields IS NULL OR :fields = '') OR name LIKE %" + ":fields" + "% OR initials LIKE %" + ":fields" + "% )"
    , nativeQuery = true, countProjection = "*")
    Page<PoliticalParyEntity> findAllWithFilters(String fields, Pageable pageable);
}