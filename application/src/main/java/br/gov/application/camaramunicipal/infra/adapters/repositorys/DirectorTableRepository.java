package br.gov.application.camaramunicipal.infra.adapters.repositorys;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return toDirectorTable(repository.findAll());
    }

    @Override
    public List<DirectorTable> findAllWithFilters(Long legislatureId, Long adjutancyId, Long parliamentaryId) {
        return toDirectorTable(repository.findAllWithFilters(legislatureId, adjutancyId, parliamentaryId));
    }

    @Override
    public Page<DirectorTable> findAllWithFilters(Long legislatureId, Long adjutancyId, Long parliamentaryId,
            Pageable pageable) {
        return repository.findAllWithFilters(legislatureId, adjutancyId, parliamentaryId, pageable)
                .map(this::toDirectorTable);
    }

    @Override
    public DirectorTable findById(Long id) {
        Optional<DirectorTableEntity> model = repository.findById(id);

        validIfModelExists(model);

        return model.orElse(new DirectorTableEntity()).toDirectorTable();
    }

    @Override
    public DirectorTable save(DirectorTable directorTable) {
        return repository.save(new DirectorTableEntity(directorTable)).toDirectorTable();
    }

    @Override
    public void detele(DirectorTable directorTable) {
        repository.delete(new DirectorTableEntity(directorTable));
    }

    private void validIfModelExists(Optional<?> model) {
        new FactoryExceptionNotFund().create(model, "Mesa diretora não encontrada.");
    }

    private DirectorTable toDirectorTable(DirectorTableEntity entity) {
        return entity.toDirectorTable();
    }

    private List<DirectorTable> toDirectorTable(List<DirectorTableEntity> list) {
        return list.stream().map(this::toDirectorTable).collect(Collectors.toList());
    }
}