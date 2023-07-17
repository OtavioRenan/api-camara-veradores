package br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys;

import java.sql.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.application.camaramunicipal.infra.adapters.entitys.ParliamentaryEntity;

public interface ParliamentarySpringRepository extends JpaRepository<ParliamentaryEntity, Long> {
    @Query(value = "SELECT * FROM parliamentarys " +
    "WHERE ( (:politicalParyId IS NULL OR :politicalParyId = '') OR political_pary_id = :politicalParyId ) " +
    "AND ( (:legislatureId IS NULL OR :legislatureId = '') OR legislature_id = :legislatureId )" +
    "AND ( (:birth IS NULL OR :birth = '') OR birth = :birth ) " +
    "AND ( (:fields IS NULL OR :fields = '') OR (nome LIKE %" + ":fields" + "% OR social_name LIKE %" + ":fields" + "%) OR (email LIKE %" + ":fields" + "%) OR (number_phone LIKE %" + ":fields" + "%)"
    , nativeQuery = true)
    List<ParliamentaryEntity> findAllWithFilters(Long politicalParyId, Long legislatureId, Date birth, String fields);

    @Query(value = "SELECT * FROM parliamentarys " +
    "WHERE ( (:politicalParyId IS NULL OR :politicalParyId = '') OR political_pary_id = :politicalParyId ) " +
    "AND ( (:legislatureId IS NULL OR :legislatureId = '') OR legislature_id = :legislatureId )" +
    "AND ( (:birth IS NULL OR :birth = '') OR birth = :birth ) " +
    "AND ( (:fields IS NULL OR :fields = '') OR (nome LIKE %" + ":fields" + "% OR social_name LIKE %" + ":fields" + "%) OR (email LIKE %" + ":fields" + "%) OR (number_phone LIKE %" + ":fields" + "%)"
    , nativeQuery = true, countProjection = "*")
    Page<ParliamentaryEntity> findAllWithFilters(Long politicalParyId, Long legislatureId, Date birth, String fields, Pageable pageable);
}