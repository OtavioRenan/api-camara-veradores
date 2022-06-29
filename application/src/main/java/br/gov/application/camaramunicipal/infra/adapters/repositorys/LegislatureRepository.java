package br.gov.application.camaramunicipal.infra.adapters.repositorys;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.gov.application.camaramunicipal.domain.Legislature;
import br.gov.application.camaramunicipal.domain.ports.repositorys.LegislaureRepositoryPort;
import br.gov.application.camaramunicipal.infra.adapters.entitys.LegislatureEntity;
import br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys.LegislatureSpringRepository;
import br.gov.application.camaramunicipal.utils.FactoryExceptionNotFund;

@Component
public class LegislatureRepository implements LegislaureRepositoryPort {
    
    private final LegislatureSpringRepository repository;

    public LegislatureRepository(LegislatureSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Legislature> findAll() {
        List<LegislatureEntity> models = this.repository.findAll();

        return models.stream().map( LegislatureEntity::toLegislature ).collect(Collectors.toList());
    }

    @Override
    public Legislature findById(Long id) {
        Optional<LegislatureEntity> model = this.repository.findById(id);

        validIfModelExists(model);

        return model.orElse( new LegislatureEntity() ).toLegislature();
    }

    @Override
    public Legislature save(Legislature legislature) {
        return this.repository.save( new LegislatureEntity(legislature) ).toLegislature();
    }

    @Override
    public void deteleById(Long id) {
        Optional<LegislatureEntity> model = this.repository.findById(id);

        validIfModelExists(model);
        
        this.repository.deleteById(id);
    }

    private void validIfModelExists(Optional<?> model) {
        new FactoryExceptionNotFund().create(model, "Legislatura não encontrada.");
    }
}