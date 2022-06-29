package br.gov.application.camaramunicipal.infra.adapters.repositorys;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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
        List<CommissionEntity> models = this.repository.findAll();

        return models.stream().map( CommissionEntity::toCommission ).collect(Collectors.toList());
    }

    @Override
    public Commission findById(Long id) {
        Optional<CommissionEntity> model = this.repository.findById(id);

        validIfModelExists(model);

        return model.orElse( new CommissionEntity() ).toCommission();
    }

    @Override
    public Commission save(Commission commission) {
        return this.repository.save( new CommissionEntity(commission) ).toCommission();
    }

    @Override
    public void deteleById(Long id) {
        Optional<CommissionEntity> model = this.repository.findById(id);

        validIfModelExists(model);
        
        this.repository.deleteById(id);
    }

    private void validIfModelExists(Optional<?> model) {
        new FactoryExceptionNotFund().create(model, "Comissão não encontrada.");
    }
}