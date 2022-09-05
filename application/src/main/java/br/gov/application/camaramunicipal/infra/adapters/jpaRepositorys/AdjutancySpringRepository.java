package br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.application.camaramunicipal.infra.adapters.entitys.AdjutancyEntity;

@Repository
public interface AdjutancySpringRepository extends JpaRepository<AdjutancyEntity, Long> {
    @Query(value = "SELECT * FROM adjutancys LIMIT :limit", nativeQuery = true)
    List<AdjutancyEntity> findAllLimit(int limit);
}