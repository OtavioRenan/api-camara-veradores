package br.gov.application.camaramunicipal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gov.application.camaramunicipal.models.MesaDiretoraModel;
import br.gov.application.camaramunicipal.repositorys.MesaDiretoraRepository;
import br.gov.application.camaramunicipal.utils.ServiceUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MesaDiretoraService
{
    private final MesaDiretoraRepository repository;

    private final ServiceUtil util;

    public List<MesaDiretoraModel> findAll()
    {
        return this.repository.findAll();
    }

    public MesaDiretoraModel find(Long id)
    {
        Optional<MesaDiretoraModel> model = this.repository.findById(id);
        
        this.util.modelExists(model);
        
        return model.get();
    }

    public MesaDiretoraModel save(MesaDiretoraModel model)
    {
        model.setCreatedAt(this.util.getDateNow());

        return this. repository.save(model);
    }

    public MesaDiretoraModel update(MesaDiretoraModel model, Long id)
    {
        Optional<MesaDiretoraModel> oldModel = this.repository.findById(id);
        
        this.util.modelExists(oldModel);

        model.setId(id);
        model.setCreatedAt(oldModel.get().getCreatedAt());
        model.setUpdatedAt(this.util.getDateNow());

        return this.repository.save(model);
    }

    public void delete(Long id)
    {
        Optional<MesaDiretoraModel> model = this.repository.findById(id);

        this.util.modelExists(model);

        this.repository.deleteById(id);
    }
}