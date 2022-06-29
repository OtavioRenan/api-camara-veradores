package br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.gov.application.camaramunicipal.infra.adapters.entitys.ParliamentaryEntity;

@Repository
public interface ParliamentarySpringRepository extends JpaRepository<ParliamentaryEntity, Long> { }