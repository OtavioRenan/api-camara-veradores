package br.gov.application.camaramunicipal.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gov.application.camaramunicipal.models.MesaDiretoraModel;
import br.gov.application.camaramunicipal.repositorys.MesaDiretoraRepository;
import br.gov.application.camaramunicipal.utils.FactoryExceptionNotFund;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MesaDiretoraService
{
    private final MesaDiretoraRepository repository;

    public List<MesaDiretoraModel> findAll()
    {
        return this.repository.findAll();
    }

    public MesaDiretoraModel find(Long id)
    {
        var model = this.repository.findById(id);
        
        this.modelExists(model);
        
        return model.get();
    }

    public MesaDiretoraModel save(MesaDiretoraModel model)
    {
        model.setCreated_at(this.getDateNow());

        return this. repository.save(model);
    }

    public MesaDiretoraModel update(MesaDiretoraModel model, Long id)
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

    private void modelExists(Optional<MesaDiretoraModel> model)
    {
        new FactoryExceptionNotFund().create(model, "Mesa diretora não encontrada.");
    }

    private Timestamp getDateNow()
    {
        return new Timestamp(System.currentTimeMillis());
    }
}