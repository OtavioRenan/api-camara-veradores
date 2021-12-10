package br.gov.application.camaramunicipal.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.application.camaramunicipal.models.MesaDiretoraModel;

public interface MesaDiretoraRepository extends JpaRepository<MesaDiretoraModel, Long>
{
    Optional<MesaDiretoraModel> findByIdLegislatura(Long id_legislatura);
    Optional<MesaDiretoraModel> findByIdCargo(Long id_cargo);
    Optional<MesaDiretoraModel> findByIdVereador(Long id_vereador);
}
