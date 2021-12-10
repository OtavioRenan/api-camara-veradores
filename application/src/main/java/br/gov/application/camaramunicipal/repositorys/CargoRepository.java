package br.gov.application.camaramunicipal.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.application.camaramunicipal.models.CargoModel;

public interface CargoRepository extends JpaRepository<CargoModel, Long>
{
    Optional<CargoModel> findByNome(String nome);
    Optional<CargoModel> findByDescricao(String descricao);
}
