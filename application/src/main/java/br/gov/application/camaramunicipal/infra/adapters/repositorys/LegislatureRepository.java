package br.gov.application.camaramunicipal.infra.adapters.repositorys;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import br.gov.application.camaramunicipal.domain.Legislature;
import br.gov.application.camaramunicipal.domain.ports.repositorys.LegislatureRepositoryPort;
import br.gov.application.camaramunicipal.infra.adapters.entitys.LegislatureEntity;
import br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys.LegislatureSpringRepository;
import br.gov.application.camaramunicipal.utils.FactoryExceptionNotFund;

@Component
public class LegislatureRepository implements LegislatureRepositoryPort {
    
    private final LegislatureSpringRepository repository;

    public LegislatureRepository(LegislatureSpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Legislature> findAll() {
        return toListLegislature(
            repository.findAll());
    }

    @Override
    public List<Legislature> findAllLimit(int limit) {
        return toListLegislature(
            repository.findAllLimit(limit));
    }

    @Override
    public Page<Legislature> findAll(int offSet, int pageSize) {
        Page<LegislatureEntity> models = repository.findAll(PageRequest.of(offSet, pageSize));

        return models.map( LegislatureEntity::toLegislature );
    }

    @Override
    public Legislature findById(Long id) {
        Optional<LegislatureEntity> model = repository.findById(id);

        validIfModelExists(model);

        return model.orElse( new LegislatureEntity() ).toLegislature();
    }

    @Override
    public Legislature save(Legislature legislature) {
        return repository.save( new LegislatureEntity(legislature) ).toLegislature();
    }

    @Override
    public void detele(Legislature legislature) {
        repository.delete( new LegislatureEntity(legislature) );
    }

    private void validIfModelExists(Optional<?> model) {
        new FactoryExceptionNotFund().create(model, "Legislatura não encontrada.");
    }

    private List<Legislature> toListLegislature(List<LegislatureEntity> list) {
        return list.stream().map( LegislatureEntity::toLegislature ).collect(Collectors.toList());
    }
}