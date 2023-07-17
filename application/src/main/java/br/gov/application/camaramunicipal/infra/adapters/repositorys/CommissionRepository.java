package br.gov.application.camaramunicipal.infra.adapters.repositorys;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
        return toCommission(repository.findAll());
    }

    @Override
    public List<Commission> findAllWithFilters(String fields) {
        return toCommission(repository.findAllWithFilters(fields));
    }

    @Override
    public Page<Commission> findAllWithFilters(String fields, Pageable pageable) {
        return repository.findAllWithFilters(fields, pageable).map(this::toCommission);
    }

    @Override
    public Commission findById(Long id) {
        Optional<CommissionEntity> model = repository.findById(id);

        validIfModelExists(model);

        return model.orElse(new CommissionEntity()).toCommission();
    }

    @Override
    public Commission save(Commission commission) {
        return repository.save(new CommissionEntity(commission)).toCommission();
    }

    @Override
    public void detele(Commission commission) {
        repository.delete(new CommissionEntity(commission));
    }

    private void validIfModelExists(Optional<?> model) {
        new FactoryExceptionNotFund().create(model, "Comissão não encontrada.");
    }

    private Commission toCommission(CommissionEntity entity) {
        return entity.toCommission();
    }

    private List<Commission> toCommission(List<CommissionEntity> list) {
        return list.stream().map(this::toCommission).collect(Collectors.toList());
    }
}