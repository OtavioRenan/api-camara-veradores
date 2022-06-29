package br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.application.camaramunicipal.infra.adapters.entitys.CommissionEntity;

@Repository
public interface CommissionSpringRepositpry extends JpaRepository<CommissionEntity, Long> { }
