package br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.application.camaramunicipal.infra.adapters.entitys.ParliamentaryEntity;

@Repository
public interface ParliamentarySpringRepository extends JpaRepository<ParliamentaryEntity, Long> {
    @Query(value = "SELECT * FROM parliamentarys LIMIT :limit", nativeQuery = true)
    List<ParliamentaryEntity> findAllLimit(int limit);
}