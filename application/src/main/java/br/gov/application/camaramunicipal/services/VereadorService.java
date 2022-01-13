package br.gov.application.camaramunicipal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gov.application.camaramunicipal.models.VereadorModel;
import br.gov.application.camaramunicipal.repositorys.VereadorRepository;
import br.gov.application.camaramunicipal.utils.ServiceUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VereadorService
{
    private final VereadorRepository repository;

    private final ServiceUtil util;

    public List<VereadorModel> findAll()
    {
        return this.repository.findAll();
    }

    public VereadorModel find(Long id)
    {
        Optional<VereadorModel> model = this.repository.findById(id);
        
        this.util.modelExists(model);
        
        return model.get();
    }

    public VereadorModel save(VereadorModel model)
    {
        model.setCreatedAt(this.util.getDateNow());

        return this. repository.save(model);
    }

    public VereadorModel update(VereadorModel model, Long id)
    {
        Optional<VereadorModel> oldModel = this.repository.findById(id);
        
        this.util.modelExists(oldModel);

        model.setId(id);
        model.setCreatedAt(oldModel.get().getCreatedAt());
        model.setUpdatedAt(this.util.getDateNow());

        return this.repository.save(model);
    }

    public void delete(Long id)
    {
        Optional<VereadorModel> model = this.repository.findById(id);

        this.util.modelExists(model);

        this.repository.deleteById(id);
    }
}