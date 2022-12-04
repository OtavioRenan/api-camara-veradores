package br.gov.application.camaramunicipal.infra.adapters.repositorys;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        return toListPoliticalPary(
            repository.findAll());
    }

    @Override
    public List<PoliticalPary> findAllLimit(int limit) {
        return toListPoliticalPary(
            repository.findAllLimit(limit));
    }

    @Override
    public List<PoliticalPary> findAllWithFilters(String fields) {
        return toListPoliticalPary(
            repository.findAllWithFilters(fields));
    }

    @Override
    public Page<PoliticalPary> findAll(int offSet, int pageSize) {
        Page<PoliticalParyEntity> models = repository.findAll(PageRequest.of(offSet, pageSize));

        return models.map( PoliticalParyEntity::toPoliticalPary );
    }

    @Override
    public PoliticalPary findById(Long id) {
        Optional<PoliticalParyEntity> model = repository.findById(id);

        validIfModelExists(model);

        return model.orElse( new PoliticalParyEntity() ).toPoliticalPary();
    }

    @Override
    public PoliticalPary save(PoliticalPary politicalPary) {
        return repository.save( new PoliticalParyEntity(politicalPary) ).toPoliticalPary();
    }

    @Override
    public void detele(PoliticalPary politicalPary) {
        repository.delete( new PoliticalParyEntity(politicalPary) );
    }

    private void validIfModelExists(Optional<?> model) {
        new FactoryExceptionNotFund().create(model, "Partido não encontrado.");
    }

    private List<PoliticalPary> toListPoliticalPary(List<PoliticalParyEntity> list) {
        return list.stream().map( PoliticalParyEntity::toPoliticalPary ).collect(Collectors.toList());
    }
}