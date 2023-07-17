package br.gov.application.camaramunicipal.infra.adapters.repositorys;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import br.gov.application.camaramunicipal.domain.Adjutancy;
import br.gov.application.camaramunicipal.domain.ports.repositorys.AdjutancyRepositoryPort;
import br.gov.application.camaramunicipal.infra.adapters.entitys.AdjutancyEntity;
import br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys.AdjutancySpringRepository;
import br.gov.application.camaramunicipal.utils.FactoryExceptionNotFund;

@Component
public class AdjutancyRepository implements AdjutancyRepositoryPort {

    private final AdjutancySpringRepository repository;

    public AdjutancyRepository(AdjutancySpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Adjutancy> findAll() {
        return toAdjuntacy(repository.findAll());
    }

    @Override
    public List<Adjutancy> findAllWithFilters(String fields) {
        return toAdjuntacy(repository.findAllWithFilters(fields));
    }

    @Override
    public Page<Adjutancy> findAllWithFilters(String fields, Pageable pageable) {
        return repository.findAllWithFilters(fields, pageable).map(this::toAdjuntacy);
    }

    @Override
    public Adjutancy findById(Long id) {
        Optional<AdjutancyEntity> model = repository.findById(id);

        validIfModelExists(model);

        return model.orElse(new AdjutancyEntity()).toAdjutancy();
    }

    @Override
    public Adjutancy save(Adjutancy adjutance) {
        return repository.save(new AdjutancyEntity(adjutance)).toAdjutancy();
    }

    @Override
    public void detele(Adjutancy adjutancy) {
        repository.delete(new AdjutancyEntity(adjutancy));
    }

    private void validIfModelExists(Optional<?> model) {
        new FactoryExceptionNotFund().create(model, "Cargo não encontrado.");
    }

    private Adjutancy toAdjuntacy(AdjutancyEntity entity) {
        return entity.toAdjutancy();
    }

    private List<Adjutancy> toAdjuntacy(List<AdjutancyEntity> list) {
        return list.stream().map(this::toAdjuntacy).collect(Collectors.toList());
    }
}
