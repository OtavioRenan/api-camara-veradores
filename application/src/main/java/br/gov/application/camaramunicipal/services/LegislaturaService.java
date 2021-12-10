package br.gov.application.camaramunicipal.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gov.application.camaramunicipal.models.LegislaturaModel;
import br.gov.application.camaramunicipal.repositorys.LegislaturaRepository;
import br.gov.application.camaramunicipal.utils.FactoryExceptionNotFund;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LegislaturaService
{
    private final LegislaturaRepository repository;

    public List<LegislaturaModel> findAll()
    {
        return this.repository.findAll();
    }

    public LegislaturaModel find(Long id)
    {
        var model = this.repository.findById(id);
        
        this.modelExists(model);
        
        return model.get();
    }

    public LegislaturaModel save(LegislaturaModel model)
    {
        model.setCreated_at(this.getDateNow());

        return this. repository.save(model);
    }

    public LegislaturaModel update(LegislaturaModel model, Long id)
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

    private void modelExists(Optional<LegislaturaModel> model)
    {
        new FactoryExceptionNotFund().create(model, "Legislatura não encontrada.");
    }

    private Timestamp getDateNow()
    {
        return new Timestamp(System.currentTimeMillis());
    }
}