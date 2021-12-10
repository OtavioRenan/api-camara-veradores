package br.gov.application.camaramunicipal.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gov.application.camaramunicipal.models.CargoModel;
import br.gov.application.camaramunicipal.repositorys.CargoRepository;
import br.gov.application.camaramunicipal.utils.FactoryExceptionNotFund;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CargoService
{
    private final CargoRepository repository;

    public List<CargoModel> findAll()
    {
        return this.repository.findAll();
    }

    public CargoModel find(Long id)
    {
        var model = this.repository.findById(id);
        
        this.modelExists(model);
        
        return model.get();
    }

    public CargoModel save(CargoModel model)
    {
        model.setCreated_at(this.getDateNow());

        return this. repository.save(model);
    }

    public CargoModel update(CargoModel model, Long id)
    {
        var oldModel = this.repository.findById(id);
        
        this.modelExists(oldModel);

        model.setId(id);
        model.setCreated_at(oldModel.get().getCreated_at());
        model.setUpdated_at(this.getDateNow());

        return this.repository.save(model);
    }

    public void delete(Long id)
    {
        var model = this.repository.findById(id);

        this.modelExists(model);

        this.repository.deleteById(id);
    }

    private void modelExists(Optional<CargoModel> model)
    {
        new FactoryExceptionNotFund().create(model, "Cargo não encontrado.");
    }

    private Timestamp getDateNow()
    {
        return new Timestamp(System.currentTimeMillis());
    }
}