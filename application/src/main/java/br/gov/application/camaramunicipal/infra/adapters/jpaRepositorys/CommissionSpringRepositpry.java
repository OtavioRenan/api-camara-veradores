package br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import br.gov.application.camaramunicipal.infra.adapters.entitys.CommissionEntity;

@Repository
public interface CommissionSpringRepositpry extends JpaRepository<CommissionEntity, Long> {
    @Query(value = "SELECT * FROM commissions LIMIT :limit", nativeQuery = true)
    List<CommissionEntity> findAllLimit(int limit);
}
