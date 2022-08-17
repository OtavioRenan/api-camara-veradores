package br.gov.application.camaramunicipal.domain.adapters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.gov.application.camaramunicipal.domain.Adjutancy;
import br.gov.application.camaramunicipal.domain.dtos.AdjutancyDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.AdjutancySimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.AdjutancyServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.AdjutancyRepositoryPort;
import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;

public class AdjutancyServiceImp implements AdjutancyServicePort {

    private final AdjutancyRepositoryPort repository;

    private static final FactoryFormatDateUtil dateUtil = new FactoryFormatDateUtil();

    public AdjutancyServiceImp(AdjutancyRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<AdjutancySimpleDTO> findAll(Map<String, String> inputs) {
        List<Adjutancy> models = repository.findAll();

        return models.stream().map( Adjutancy::toAdjutancySimpleDTO ).collect(Collectors.toList());
    }

    @Override
    public Page<AdjutancySimpleDTO> findAll(Map<String, String> inputs, int offSet, int pageSize) {
        Page<Adjutancy> models = repository.findAll(offSet, pageSize);

        return models.map( Adjutancy::toAdjutancySimpleDTO );
    }

    @Override
    public AdjutancyDTO findById(Long id) {
        Adjutancy model = repository.findById(id);

        return model.toAdjutancyDTO();
    }

    @Override
    public AdjutancyDTO save(AdjutancyDTO dto) {
        Adjutancy model = new Adjutancy(dto);
        model.setCreatedAt(dateUtil.getNowWithZoneIdBr());

        return repository.save(model).toAdjutancyDTO();
    }

    @Override
    public AdjutancyDTO save(AdjutancyDTO dto, Long id) {

        Adjutancy oldModel = repository.findById(id);

        Adjutancy model = new Adjutancy(dto);
        model.setCreatedAt(oldModel.getCreatedAt());
        model.setUpdatedAt(dateUtil.getNowWithZoneIdBr());
        model.setId(id);

        return repository.save(model).toAdjutancyDTO();
    }

    @Override
    public void delete(Long id) {
        this.repository.deteleById(id);
    }
}
