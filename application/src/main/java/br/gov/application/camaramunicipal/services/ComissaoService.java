package br.gov.application.camaramunicipal.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gov.application.camaramunicipal.models.ComissaoModel;
import br.gov.application.camaramunicipal.repositorys.ComissaoRepository;
import br.gov.application.camaramunicipal.utils.FactoryExceptionNotFund;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ComissaoService
{
    private final ComissaoRepository repository;

    public List<ComissaoModel> findAll()
    {
        return this.repository.findAll();
    }

    public ComissaoModel find(Long id)
    {
        var model = this.repository.findById(id);
        
        this.modelExists(model);
        
        return model.get();
    }

    public ComissaoModel save(ComissaoModel model)
    {
        model.setCreated_at(this.getDateNow());

        return this. repository.save(model);
    }

    public ComissaoModel update(ComissaoModel model, Long id)
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

    private void modelExists(Optional<ComissaoModel> model)
    {
        new FactoryExceptionNotFund().create(model, "Comissão não encontrada.");
    }

    private Timestamp getDateNow()
    {
        return new Timestamp(System.currentTimeMillis());
    }
}