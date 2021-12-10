package br.gov.application.camaramunicipal.repositorys;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.gov.application.camaramunicipal.models.ComissaoModel;

public interface ComissaoRepository extends JpaRepository<ComissaoModel, Long>
{
    Optional<ComissaoModel> findByNome(String nome);
    Optional<ComissaoModel> findByDescricao(String descricao);
}
