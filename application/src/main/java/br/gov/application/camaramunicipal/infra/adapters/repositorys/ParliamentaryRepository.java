package br.gov.application.camaramunicipal.infra.adapters.repositorys;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
        return toListParliamentary(
            repository.findAll());
    }

    @Override
    public List<Parliamentary> findAllLimit(int limit) {
        return toListParliamentary(
            repository.findAllLimit(limit));
    }

    @Override
    public Page<Parliamentary> findAll(int offSet, int pageSize) {
        Page<ParliamentaryEntity> models = repository.findAll(PageRequest.of(offSet, pageSize));

        return models.map( ParliamentaryEntity::toParliamentary );
    }

    @Override
    public Parliamentary findById(Long id) {
        Optional<ParliamentaryEntity> model = repository.findById(id);

        validIfModelExists(model);

        return model.orElse( new ParliamentaryEntity() ).toParliamentary();
    }

    @Override
    public Parliamentary save(Parliamentary parliamentary) {
        return repository.save( new ParliamentaryEntity(parliamentary) ).toParliamentary();
    }

    @Override
    public void detele(Parliamentary parliamentary) {        
        repository.delete( new ParliamentaryEntity(parliamentary) );
    }

    private void validIfModelExists(Optional<?> model) {
        new FactoryExceptionNotFund().create(model, "Parlamentar não encontrado.");
    }

    private List<Parliamentary> toListParliamentary(List<ParliamentaryEntity> list) {
        return list.stream().map( ParliamentaryEntity::toParliamentary ).collect(Collectors.toList());
    }
}