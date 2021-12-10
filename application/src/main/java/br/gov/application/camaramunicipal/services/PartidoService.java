package br.gov.application.camaramunicipal.services;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gov.application.camaramunicipal.models.PartidoModel;
import br.gov.application.camaramunicipal.repositorys.PartidoRepository;
import br.gov.application.camaramunicipal.utils.FactoryExceptionNotFund;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartidoService
{
    private final PartidoRepository repository;

    public List<PartidoModel> findAll()
    {
        return this.repository.findAll();
    }

    public PartidoModel find(Long id)
    {
        var model = this.repository.findById(id);
        
        this.modelExists(model);
        
        return model.get();
    }

    public PartidoModel save(PartidoModel model)
    {
        model.setCreated_at(this.getDateNow());

        return this. repository.save(model);
    }

    public PartidoModel update(PartidoModel model, Long id)
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

    private void modelExists(Optional<PartidoModel> model)
    {
        new FactoryExceptionNotFund().create(model, "Partido não encontrado.");
    }

    private Timestamp getDateNow()
    {
        return new Timestamp(System.currentTimeMillis());
    }
}