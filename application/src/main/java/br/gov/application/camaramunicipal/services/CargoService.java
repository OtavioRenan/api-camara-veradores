package br.gov.application.camaramunicipal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gov.application.camaramunicipal.models.CargoModel;
import br.gov.application.camaramunicipal.repositorys.CargoRepository;
import br.gov.application.camaramunicipal.utils.ServiceUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CargoService
{
    private final CargoRepository repository;

    private final ServiceUtil util;

    public List<CargoModel> findAll()
    {
        return this.repository.findAll();
    }

    public CargoModel find(Long id)
    {
        Optional<CargoModel> model = this.repository.findById(id);
        
        this.util.modelExists(model);
        
        return model.get();
    }

    public CargoModel save(CargoModel model)
    {
        model.setCreatedAt(this.util.getDateNow());

        return this. repository.save(model);
    }

    public CargoModel update(CargoModel model, Long id)
    {
        Optional<CargoModel> oldModel = this.repository.findById(id);
        
        this.util.modelExists(oldModel);

        model.setId(id);
        model.setCreatedAt(oldModel.get().getCreatedAt());
        model.setUpdatedAt(this.util.getDateNow());

        return this.repository.save(model);
    }

    public void delete(Long id)
    {
        Optional<CargoModel> model = this.repository.findById(id);

        this.util.modelExists(model);

        this.repository.deleteById(id);
    }
}