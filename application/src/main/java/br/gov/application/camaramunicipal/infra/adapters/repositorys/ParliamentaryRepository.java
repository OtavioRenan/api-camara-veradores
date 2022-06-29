package br.gov.application.camaramunicipal.infra.adapters.repositorys;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import br.gov.application.camaramunicipal.domain.Parliamentary;
import br.gov.application.camaramunicipal.domain.ports.repositorys.ParliamentaryRepositoryPort;
import br.gov.application.camaramunicipal.infra.adapters.entitys.ParliamentaryEntity;
import br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys.ParliamentarySpringRepository;
import br.gov.application.camaramunicipal.utils.FactoryExceptionNotFund;

@Component
public class ParliamentaryRepository implements ParliamentaryRepositoryPort {
    private final ParliamentarySpringRepository repository;

    public ParliamentaryRepository(ParliamentarySpringRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Parliamentary> findAll() {
        List<ParliamentaryEntity> models = this.repository.findAll();

        return models.stream().map( ParliamentaryEntity::toParliamentary ).collect(Collectors.toList());
    }

    @Override
    public Parliamentary findById(Long id) {
        Optional<ParliamentaryEntity> model = this.repository.findById(id);

        validIfModelExists(model);

        return model.orElse( new ParliamentaryEntity() ).toParliamentary();
    }

    @Override
    public Parliamentary save(Parliamentary parliamentary) {
        return this.repository.save( new ParliamentaryEntity(parliamentary) ).toParliamentary();
    }

    @Override
    public void deteleById(Long id) {
        Optional<ParliamentaryEntity> model = this.repository.findById(id);

        validIfModelExists(model);
        
        this.repository.deleteById(id);
    }

    private void validIfModelExists(Optional<?> model) {
        new FactoryExceptionNotFund().create(model, "Parlamentar não encontrado.");
    }
}