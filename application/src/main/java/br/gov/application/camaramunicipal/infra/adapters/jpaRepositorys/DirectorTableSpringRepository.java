package br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.application.camaramunicipal.infra.adapters.entitys.DirectorTableEntity;

@Repository
public interface DirectorTableSpringRepository extends JpaRepository<DirectorTableEntity, Long>{ }
