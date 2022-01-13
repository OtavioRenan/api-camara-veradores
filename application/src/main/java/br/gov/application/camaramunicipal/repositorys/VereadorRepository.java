package br.gov.application.camaramunicipal.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.application.camaramunicipal.models.VereadorModel;

public interface VereadorRepository extends JpaRepository<VereadorModel, Long>
{
    Optional<VereadorModel> findByNome(String nome);
    Optional<VereadorModel> findByNomeSocial(String nome);
}
