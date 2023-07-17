package br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.application.camaramunicipal.infra.adapters.entitys.LegislatureEntity;

public interface LegislatureSpringRepository extends JpaRepository<LegislatureEntity, Long> {
    @Query(value = "SELECT * FROM legislatures " +
    "WHERE ( (:fields IS NULL OR :fields = '') OR description LIKE %" + ":fields" + "% ) " +
    "AND ( (:dateStart IS NULL OR :dateStart = '') OR date_start >= :dateStart )" +
    "AND ( (:dateEnd IS NULL OR :dateEnd = '') OR date_end <= :dateEnd )"
    , nativeQuery = true)
    List<LegislatureEntity> findAllWithFilters(String fields, Date dateStart, Date dateEnd);

    @Query(value = "SELECT * FROM legislatures " +
    "WHERE ( (:fields IS NULL OR :fields = '') OR description LIKE %" + ":fields" + "% ) " +
    "AND ( (:dateStart IS NULL OR :dateStart = '') OR date_start >= :dateStart )" +
    "AND ( (:dateEnd IS NULL OR :dateEnd = '') OR date_end <= :dateEnd )"
    , nativeQuery = true, countProjection = "*")
    Page<LegislatureEntity> findAllWithFilters(String fields, Date dateStart, Date dateEnd, Pageable pageable);
}