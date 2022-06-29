package br.gov.application.camaramunicipal.infra.adapters.repositorys;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<PoliticalParyEntity> models = this.repository.findAll();

        return models.stream().map( PoliticalParyEntity::toPoliticalPary ).collect(Collectors.toList());
    }

    @Override
    public PoliticalPary findById(Long id) {
        Optional<PoliticalParyEntity> model = this.repository.findById(id);

        validIfModelExists(model);

        return model.orElse( new PoliticalParyEntity() ).toPoliticalPary();
    }

    @Override
    public PoliticalPary save(PoliticalPary politicalPary) {
        return this.repository.save( new PoliticalParyEntity(politicalPary) ).toPoliticalPary();
    }

    @Override
    public void deteleById(Long id) {
        Optional<PoliticalParyEntity> model = this.repository.findById(id);

        validIfModelExists(model);
        
        this.repository.deleteById(id);
    }

    private void validIfModelExists(Optional<?> model) {
        new FactoryExceptionNotFund().create(model, "Partido não encontrado.");
    }
}