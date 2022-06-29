package br.gov.application.camaramunicipal.infra.adapters.repositorys;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.gov.application.camaramunicipal.domain.DirectorTable;
import br.gov.application.camaramunicipal.domain.ports.repositorys.DirectorTableRepositoryPort;
import br.gov.application.camaramunicipal.infra.adapters.entitys.DirectorTableEntity;
import br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys.DirectorTableSpringRepository;
import br.gov.application.camaramunicipal.utils.FactoryExceptionNotFund;

@Component
public class DirectorTableRepository implements DirectorTableRepositoryPort {
    private final DirectorTableSpringRepository repository;

    public DirectorTableRepository(DirectorTableSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<DirectorTable> findAll() {
        List<DirectorTableEntity> models = this.repository.findAll();

        return models.stream().map( DirectorTableEntity::toDirectorTable ).collect(Collectors.toList());
    }

    @Override
    public DirectorTable findById(Long id) {
        Optional<DirectorTableEntity> model = this.repository.findById(id);

        validIfModelExists(model);

        return model.orElse( new DirectorTableEntity() ).toDirectorTable();
    }

    @Override
    public DirectorTable save(DirectorTable directorTable) {
        return this.repository.save( new DirectorTableEntity(directorTable) ).toDirectorTable();
    }

    @Override
    public void deteleById(Long id) {
        Optional<DirectorTableEntity> model = this.repository.findById(id);

        validIfModelExists(model);
        
        this.repository.deleteById(id);
    }

    private void validIfModelExists(Optional<?> model) {
        new FactoryExceptionNotFund().create(model, "Mesa diretora não encontrada.");
    }
}