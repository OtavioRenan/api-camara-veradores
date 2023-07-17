package br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.application.camaramunicipal.infra.adapters.entitys.DirectorTableEntity;

public interface DirectorTableSpringRepository extends JpaRepository<DirectorTableEntity, Long> {
    @Query(value = "SELECT * FROM directors_tables " +
    "WHERE ( (:legislatureId IS NULL OR :legislatureId = '') OR legislature_id = :legislatureId ) " +
    "AND ( (:adjutancyId IS NULL OR :adjutancyId = '') OR adjutancy_id = :adjutancyId )" +
    "AND ( (:parliamentaryId IS NULL OR :parliamentaryId = '') OR parliamentary_id = :parliamentaryId )"
    , nativeQuery = true)
    List<DirectorTableEntity> findAllWithFilters(Long legislatureId, Long adjutancyId, Long parliamentaryId);

    @Query(value = "SELECT * FROM directors_tables " +
    "WHERE ( (:legislatureId IS NULL OR :legislatureId = '') OR legislature_id = :legislatureId ) " +
    "AND ( (:adjutancyId IS NULL OR :adjutancyId = '') OR adjutancy_id = :adjutancyId )" +
    "AND ( (:parliamentaryId IS NULL OR :parliamentaryId = '') OR parliamentary_id = :parliamentaryId )"
    , nativeQuery = true, countProjection = "*")
    Page<DirectorTableEntity> findAllWithFilters(Long legislatureId, Long adjutancyId, Long parliamentaryId, Pageable pageable);
}