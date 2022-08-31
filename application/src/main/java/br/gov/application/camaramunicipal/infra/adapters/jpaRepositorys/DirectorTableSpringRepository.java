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
}
