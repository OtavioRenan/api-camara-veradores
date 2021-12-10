package br.gov.application.camaramunicipal.services;

import java.sql.Timestamp;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gov.application.camaramunicipal.models.VereadorModel;
import br.gov.application.camaramunicipal.repositorys.VereadorRepository;
import br.gov.application.camaramunicipal.utils.FactoryExceptionNotFund;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VereadorService
{
    private final VereadorRepository repository;

    public List<VereadorModel> findAll()
    {
        return this.repository.findAll();
    }

    public VereadorModel find(Long id)
    {
        var model = this.repository.findById(id);
        
        this.modelExists(model);
        
        return model.get();
    }

    public VereadorModel save(VereadorModel model)
    {
        model.setCreated_at(this.getDateNow());

        return this. repository.save(model);
    }

    public VereadorModel update(VereadorModel model, Long id)
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

    private void modelExists(Optional<VereadorModel> model)
    {
        new FactoryExceptionNotFund().create(model, "Vereador(a) não encontrado.");
    }

    private Timestamp getDateNow()
    {
        return new Timestamp(System.currentTimeMillis());
    }
}