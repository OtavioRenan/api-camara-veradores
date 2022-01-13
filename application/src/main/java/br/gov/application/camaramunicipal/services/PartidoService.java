package br.gov.application.camaramunicipal.services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.gov.application.camaramunicipal.models.PartidoModel;
import br.gov.application.camaramunicipal.repositorys.PartidoRepository;
import br.gov.application.camaramunicipal.utils.ServiceUtil;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PartidoService
{
    private final PartidoRepository repository;

    private final ServiceUtil util;

    public List<PartidoModel> findAll()
    {
        return this.repository.findAll();
    }

    public PartidoModel find(Long id)
    {
        Optional<PartidoModel> model = this.repository.findById(id);
        
        this.util.modelExists(model);
        
        return model.get();
    }

    public PartidoModel save(PartidoModel model)
    {
        model.setCreatedAt(this.util.getDateNow());

        return this. repository.save(model);
    }

    public PartidoModel update(PartidoModel model, Long id)
    {
        Optional<PartidoModel> oldModel = this.repository.findById(id);
        
        this.util.modelExists(oldModel);

        model.setId(id);
        model.setCreatedAt(oldModel.get().getCreatedAt());
        model.setUpdatedAt(this.util.getDateNow());

        return this.repository.save(model);
    }

    public void delete(Long id)
    {
        Optional<PartidoModel> model = this.repository.findById(id);

        this.util.modelExists(model);

        this.repository.deleteById(id);
    }
}