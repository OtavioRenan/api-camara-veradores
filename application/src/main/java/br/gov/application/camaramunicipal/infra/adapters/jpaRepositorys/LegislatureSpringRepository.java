package br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys;

import org.springframework.stereotype.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.gov.application.camaramunicipal.infra.adapters.entitys.LegislatureEntity;

@Repository
public interface LegislatureSpringRepository extends JpaRepository<LegislatureEntity, Long> {
    @Query(value = "SELECT * FROM legislatures LIMIT :limit", nativeQuery = true)
    List<LegislatureEntity> findAllLimit(int limit);
}