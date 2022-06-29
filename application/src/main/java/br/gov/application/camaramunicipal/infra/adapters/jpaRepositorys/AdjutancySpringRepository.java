package br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.application.camaramunicipal.infra.adapters.entitys.AdjutancyEntity;

@Repository
public interface AdjutancySpringRepository extends JpaRepository<AdjutancyEntity, Long> { }
