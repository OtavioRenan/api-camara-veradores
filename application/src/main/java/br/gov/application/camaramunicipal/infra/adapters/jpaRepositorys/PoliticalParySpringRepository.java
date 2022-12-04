package br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.application.camaramunicipal.infra.adapters.entitys.PoliticalParyEntity;

@Repository
public interface PoliticalParySpringRepository extends JpaRepository<PoliticalParyEntity, Long> {
    @Query(value = "SELECT * FROM politicals_parys LIMIT :limit", nativeQuery = true)
    List<PoliticalParyEntity> findAllLimit(int limit);

    @Query(value = "SELECT * FROM politicals_parys " +
    "WHERE ( (:fields IS NULL OR :fields = '') OR name LIKE %" + ":fields" + "% OR initials LIKE %" + ":fields" + "% )"
    , nativeQuery = true)
    List<PoliticalParyEntity> findAllWithFilters(String fields);
}