package br.gov.application.camaramunicipal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gov.application.camaramunicipal.models.ComissaoModel;
import br.gov.application.camaramunicipal.repositorys.ComissaoRepository;
import br.gov.application.camaramunicipal.utils.ServiceUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComissaoService
{
    private final ComissaoRepository repository;

    private final ServiceUtil util;

    public List<ComissaoModel> findAll()
    {
        return this.repository.findAll();
    }

    public ComissaoModel find(Long id)
    {
        Optional<ComissaoModel> model = this.repository.findById(id);
        
        this.util.modelExists(model);
        
        return model.get();
    }

    public ComissaoModel save(ComissaoModel model)
    {
        model.setCreatedAt(this.util.getDateNow());

        return this. repository.save(model);
    }

    public ComissaoModel update(ComissaoModel model, Long id)
    {
        Optional<ComissaoModel> oldModel = this.repository.findById(id);
        
        this.util.modelExists(oldModel);

        model.setId(id);
        model.setCreatedAt(oldModel.get().getCreatedAt());
        model.setUpdatedAt(this.util.getDateNow());

        return this.repository.save(model);
    }

    public void delete(Long id)
    {
        Optional<ComissaoModel> model = this.repository.findById(id);

        this.util.modelExists(model);

        this.repository.deleteById(id);
    }
}