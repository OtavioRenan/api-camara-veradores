package br.gov.application.camaramunicipal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gov.application.camaramunicipal.models.LegislaturaModel;
import br.gov.application.camaramunicipal.repositorys.LegislaturaRepository;
import br.gov.application.camaramunicipal.utils.ServiceUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LegislaturaService
{
    private final LegislaturaRepository repository;

    private final ServiceUtil util;

    public List<LegislaturaModel> findAll()
    {
        return this.repository.findAll();
    }

    public LegislaturaModel find(Long id)
    {
        Optional<LegislaturaModel> model = this.repository.findById(id);
        
        this.util.modelExists(model);
        
        return model.get();
    }

    public LegislaturaModel save(LegislaturaModel model)
    {
        model.setCreatedAt(this.util.getDateNow());

        return this. repository.save(model);
    }

    public LegislaturaModel update(LegislaturaModel model, Long id)
    {
        Optional<LegislaturaModel> oldModel = this.repository.findById(id);
        
        this.util.modelExists(oldModel);

        model.setId(id);
        model.setCreatedAt(oldModel.get().getCreatedAt());
        model.setUpdatedAt(this.util.getDateNow());

        return this.repository.save(model);
    }

    public void delete(Long id)
    {
        Optional<LegislaturaModel> model = this.repository.findById(id);

        this.util.modelExists(model);

        this.repository.deleteById(id);
    }
}