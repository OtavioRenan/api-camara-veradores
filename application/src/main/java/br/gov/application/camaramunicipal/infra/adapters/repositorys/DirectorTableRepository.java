package br.gov.application.camaramunicipal.infra.adapters.repositorys;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        return toListDirectorTable(
            repository.findAll());
    }

    @Override
    public List<DirectorTable> findAllLimit(int limit) {
        return toListDirectorTable(
            repository.findAllLimit(limit));
    }

    @Override
    public List<DirectorTable> findAllWithFilters(Long legislatureId, Long adjutancyId, Long parliamentaryId) {
        return toListDirectorTable(
            repository.findAllWithFilters(legislatureId, adjutancyId, parliamentaryId));
    }

    @Override
    public Page<DirectorTable> findAll(int offSet, int pageSize) {
        Page<DirectorTableEntity> models = repository.findAll(PageRequest.of(offSet, pageSize));

        return models.map( DirectorTableEntity::toDirectorTable );
    }

    @Override
    public DirectorTable findById(Long id) {
        Optional<DirectorTableEntity> model = repository.findById(id);

        validIfModelExists(model);

        return model.orElse( new DirectorTableEntity() ).toDirectorTable();
    }

    @Override
    public DirectorTable save(DirectorTable directorTable) {
        return repository.save( new DirectorTableEntity(directorTable) ).toDirectorTable();
    }

    @Override
    public void detele(DirectorTable directorTable) {
        repository.delete( new DirectorTableEntity(directorTable) );
    }

    private void validIfModelExists(Optional<?> model) {
        new FactoryExceptionNotFund().create(model, "Mesa diretora não encontrada.");
    }

    private List<DirectorTable> toListDirectorTable(List<DirectorTableEntity> list) {
        return list.stream().map( DirectorTableEntity::toDirectorTable ).collect(Collectors.toList());
    }
}