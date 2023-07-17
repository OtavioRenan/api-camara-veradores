package br.gov.application.camaramunicipal.infra.adapters.repositorys;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.gov.application.camaramunicipal.domain.PoliticalPary;
import br.gov.application.camaramunicipal.domain.ports.repositorys.PoliticalParyRepositoryPort;
import br.gov.application.camaramunicipal.infra.adapters.entitys.PoliticalParyEntity;
import br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys.PoliticalParySpringRepository;
import br.gov.application.camaramunicipal.utils.FactoryExceptionNotFund;

@Component
public class PoliticalParyRepository implements PoliticalParyRepositoryPort {
    private final PoliticalParySpringRepository repository;

    public PoliticalParyRepository(PoliticalParySpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PoliticalPary> findAll() {
        return toPoliticalPary(repository.findAll());
    }

    @Override
    public List<PoliticalPary> findAllWithFilters(String fields) {
        return toPoliticalPary(repository.findAllWithFilters(fields));
    }

    @Override
    public Page<PoliticalPary> findAllWithFilters(String fields, Pageable pageable) {
        return repository.findAllWithFilters(fields, pageable).map(this::toPoliticalPary);
    }

    @Override
    public PoliticalPary findById(Long id) {
        Optional<PoliticalParyEntity> model = repository.findById(id);

        validIfModelExists(model);

        return model.orElse(new PoliticalParyEntity()).toPoliticalPary();
    }

    @Override
    public PoliticalPary save(PoliticalPary politicalPary) {
        return repository.save(new PoliticalParyEntity(politicalPary)).toPoliticalPary();
    }

    @Override
    public void detele(PoliticalPary politicalPary) {
        repository.delete(new PoliticalParyEntity(politicalPary));
    }

    private void validIfModelExists(Optional<?> model) {
        new FactoryExceptionNotFund().create(model, "Partido não encontrado.");
    }

    private PoliticalPary toPoliticalPary(PoliticalParyEntity entity) {
        return entity.toPoliticalPary();
    }

    private List<PoliticalPary> toPoliticalPary(List<PoliticalParyEntity> list) {
        return list.stream().map(this::toPoliticalPary).collect(Collectors.toList());
    }
}