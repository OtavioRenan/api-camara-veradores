package br.gov.application.camaramunicipal.infra.adapters.repositorys;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.gov.application.camaramunicipal.domain.Adjutancy;
import br.gov.application.camaramunicipal.domain.ports.repositorys.AdjutancyRepositoryPort;
import br.gov.application.camaramunicipal.infra.adapters.entitys.AdjutancyEntity;
import br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys.AdjutancySpringRepository;
import br.gov.application.camaramunicipal.utils.FactoryExceptionNotFund;

@Component
public class AdjuntacyRepository implements AdjutancyRepositoryPort {

    private final AdjutancySpringRepository repository;

    public AdjuntacyRepository(AdjutancySpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Adjutancy> findAll() {
        List<AdjutancyEntity> models = this.repository.findAll();

        return models.stream().map( AdjutancyEntity::toAdjutancy ).collect(Collectors.toList());
    }

    @Override
    public Adjutancy findById(Long id) {
        Optional<AdjutancyEntity> model = this.repository.findById(id);

        validIfModelExists(model);

        return model.orElse( new AdjutancyEntity() ).toAdjutancy();
    }

    @Override
    public Adjutancy save(Adjutancy adjutance) {
        return this.repository.save( new AdjutancyEntity(adjutance) ).toAdjutancy();
    }

    @Override
    public void deteleById(Long id) {
        Optional<AdjutancyEntity> model = this.repository.findById(id);

        validIfModelExists(model);
        
        this.repository.deleteById(id);
    }

    private void validIfModelExists(Optional<?> model) {
        new FactoryExceptionNotFund().create(model, "Cargo não encontrado.");
    }
}
