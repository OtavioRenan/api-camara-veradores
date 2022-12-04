package br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.application.camaramunicipal.infra.adapters.entitys.DirectorTableEntity;

@Repository
public interface DirectorTableSpringRepository extends JpaRepository<DirectorTableEntity, Long> {
    @Query(value = "SELECT * FROM directors_tables LIMIT :limit", nativeQuery = true)
    List<DirectorTableEntity> findAllLimit(int limit);

    @Query(value = "SELECT * FROM directors_tables " +
    "WHERE ( (:legislatureId IS NULL OR :legislatureId = '') OR legislature_id = :legislatureId ) " +
    "AND ( (:adjutancyId IS NULL OR :adjutancyId = '') OR adjutancy_id = :adjutancyId )" +
    "AND ( (:parliamentaryId IS NULL OR :parliamentaryId = '') OR parliamentary_id = :parliamentaryId )"
    , nativeQuery = true)
    List<DirectorTableEntity> findAllWithFilters(Long legislatureId, Long adjutancyId, Long parliamentaryId);
}