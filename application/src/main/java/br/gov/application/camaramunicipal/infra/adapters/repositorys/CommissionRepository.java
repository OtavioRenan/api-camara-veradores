package br.gov.application.camaramunicipal.infra.adapters.repositorys;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Component;

import br.gov.application.camaramunicipal.domain.Commission;
import br.gov.application.camaramunicipal.domain.ports.repositorys.CommissionRepositoryPort;
import br.gov.application.camaramunicipal.infra.adapters.entitys.CommissionEntity;
import br.gov.application.camaramunicipal.infra.adapters.jpaRepositorys.CommissionSpringRepositpry;
import br.gov.application.camaramunicipal.utils.FactoryExceptionNotFund;

@Component
public class CommissionRepository implements CommissionRepositoryPort {
    private final CommissionSpringRepositpry repository;

    public CommissionRepository(CommissionSpringRepositpry repository) {
        this.repository = repository;
    }

    @Override
    public List<Commission> findAll() {
        return toListCommission(
            repository.findAll());
    }

    @Override
    public List<Commission> findAllLimit(int limit) {
        return toListCommission(
            repository.findAllLimit(limit));
    }

    @Override
    public Page<Commission> findAll(int offSet, int pageSize) {
        Page<CommissionEntity> models = repository.findAll(PageRequest.of(offSet, pageSize));

        return models.map( CommissionEntity::toCommission );
    }

    @Override
    public Commission findById(Long id) {
        Optional<CommissionEntity> model = repository.findById(id);

        validIfModelExists(model);

        return model.orElse( new CommissionEntity() ).toCommission();
    }

    @Override
    public Commission save(Commission commission) {
        return repository.save( new CommissionEntity(commission) ).toCommission();
    }

    @Override
    public void detele(Commission commission) {
        repository.delete(new CommissionEntity(commission));
    }

    private void validIfModelExists(Optional<?> model) {
        new FactoryExceptionNotFund().create(model, "Comissão não encontrada.");
    }

    private List<Commission> toListCommission(List<CommissionEntity> list) {
        return list.stream().map( CommissionEntity::toCommission ).collect(Collectors.toList());
    }
}