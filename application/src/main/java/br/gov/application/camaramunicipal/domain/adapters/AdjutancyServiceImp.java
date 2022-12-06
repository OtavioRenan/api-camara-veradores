package br.gov.application.camaramunicipal.domain.adapters;

import java.util.ArrayList;
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
import br.gov.application.camaramunicipal.utils.FiltersUtil;
import br.gov.application.camaramunicipal.utils.PageableUtil;

public class AdjutancyServiceImp implements AdjutancyServicePort {

    private final AdjutancyRepositoryPort repository;

    private static final FactoryFormatDateUtil dateUtil = new FactoryFormatDateUtil();

    private static final FiltersUtil filterUtil =  new FiltersUtil();

    public AdjutancyServiceImp(AdjutancyRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<AdjutancySimpleDTO> findAll(Map<String, String> inputs) {
        List<Adjutancy> models = new ArrayList<>();

        if( filterEmptry(inputs) ) {
            models.addAll( repository.findAllLimit(200) );
        } else {
            models.addAll( filter(inputs) );
        }

        return models.stream().map( Adjutancy::toAdjutancySimpleDTO ).collect(Collectors.toList());
    }

    @Override
    public Page<AdjutancySimpleDTO> findAll(Map<String, String> inputs, int offSet, int pageSize) {
        List<AdjutancySimpleDTO> models = findAll(inputs);

        return new PageableUtil().pageable(models, offSet, pageSize);
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

    List<Adjutancy> filter(Map<String, String> input) {
        
        String fields = null;

        for(Map.Entry<String, String> v : input.entrySet()) {
            if( equalsAndNoEmptry(v, "fields") ) { fields = v.getValue(); }
        }

        return repository.findAllWithFilters(fields);
    }

    private boolean equalsAndNoEmptry(Map.Entry<String, String> map, String column) {
        return filterUtil.equalsAndNoEmptry(map, column);
    }

    private boolean filterEmptry(Map<String, String> map) {
        return filterUtil.filterEmptry(map);
    }
}