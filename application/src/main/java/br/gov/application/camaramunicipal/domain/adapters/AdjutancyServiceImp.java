package br.gov.application.camaramunicipal.domain.adapters;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

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

        models = filters(inputs, models);

        return models.stream().map( Adjutancy::toAdjutancySimpleDTO ).collect(Collectors.toList());
    }

    @Override
    public Page<AdjutancySimpleDTO> findAll(Map<String, String> inputs, int offSet, int pageSize) {
        if(inputs.size() > 0) {
            List<AdjutancySimpleDTO> models = findAll(inputs);
            return new PageImpl<>(models, PageRequest.of(offSet, pageSize), models.size());
        }

        Page<Adjutancy> pages = repository.findAll(offSet, pageSize);
        return pages.map( Adjutancy::toAdjutancySimpleDTO );
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
        Adjutancy adjutancy = repository.findById(id);

        repository.detele(adjutancy);
    }

    private List<Adjutancy> filters(Map<String, String> inputs, List<Adjutancy> models) {

        String fields = isNullOrEmptry(inputs.get("fields"));

        if(!Objects.isNull(fields)) { models = filterByFields(models, fields); }

        return models;
    }

    private List<Adjutancy> filterByFields(List<Adjutancy> list, String fileds) {
        return list.stream().filter( adjutancy -> filterByFields(adjutancy, fileds) ).collect(Collectors.toList());
    }

    private boolean filterByFields(Adjutancy adjutancy, String fields) {
        return adjutancy.getName().contains(fields) || adjutancy.getDescription().contains(fields);
    }

    private String isNullOrEmptry(String str) { return (Objects.isNull(str) || str.isEmpty()) ? null : str; }
}