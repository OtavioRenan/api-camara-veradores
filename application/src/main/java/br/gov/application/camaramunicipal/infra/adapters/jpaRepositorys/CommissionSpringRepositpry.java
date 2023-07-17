package br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.application.camaramunicipal.infra.adapters.entitys.CommissionEntity;

public interface CommissionSpringRepositpry extends JpaRepository<CommissionEntity, Long> {
    @Query(value = "SELECT * FROM commissions " +
    "WHERE ( (:fields IS NULL OR :fields = '') OR name LIKE %" + ":fields" + "% " +
    "OR description LIKE %" + ":fields" + "% ) "
    , nativeQuery = true)
    List<CommissionEntity> findAllWithFilters(String fields);

    @Query(value = "SELECT * FROM commissions " +
    "WHERE ( (:fields IS NULL OR :fields = '') OR name LIKE %" + ":fields" + "% " +
    "OR description LIKE %" + ":fields" + "% ) "
    , nativeQuery = true, countProjection = "*")
    Page<CommissionEntity> findAllWithFilters(String fields, Pageable pageable);
}
