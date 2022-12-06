package br.gov.application.camaramunicipal.domain.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.gov.application.camaramunicipal.domain.PoliticalPary;
import br.gov.application.camaramunicipal.domain.dtos.PoliticalParyDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.PoliticalParySimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.PoliticalParyServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.PoliticalParyRepositoryPort;
import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;
import br.gov.application.camaramunicipal.utils.FiltersUtil;
import br.gov.application.camaramunicipal.utils.PageableUtil;

public class PoliticalParyServiceImp implements PoliticalParyServicePort {
    private final PoliticalParyRepositoryPort repository;

    private static final FactoryFormatDateUtil dateUtil = new FactoryFormatDateUtil();

    private static final FiltersUtil filterUtil =  new FiltersUtil();

    public PoliticalParyServiceImp(PoliticalParyRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<PoliticalParySimpleDTO> findAll(Map<String, String> inputs) {
        List<PoliticalPary> models = new ArrayList<>();

        if( filterEmptry(inputs) ) {
            models.addAll( repository.findAllLimit(200) );
        } else {
            models.addAll( filter(inputs) );
        }
        
        return models.stream().map( PoliticalPary::toPoliticalParySimpleDTO ).collect(Collectors.toList());
    }

    @Override
    public Page<PoliticalParySimpleDTO> findAll(Map<String, String> inputs, int offset, int pageSize) {
        List<PoliticalParySimpleDTO> models = findAll(inputs);
        
        return new PageableUtil().pageable(models, offset, pageSize);       
    }

    @Override
    public PoliticalParyDTO findById(Long id) {
        PoliticalPary model = repository.findById(id);
        
        return model.toPoliticalParyDTO();
    }

    @Override
    public PoliticalParyDTO save(PoliticalParyDTO dto) {
        PoliticalPary model = new PoliticalPary(dto);
        model.setCreatedAt(dateUtil.getNowWithZoneIdBr());

        return repository.save(model).toPoliticalParyDTO();
    }

    @Override
    public PoliticalParyDTO save(PoliticalParyDTO dto, Long id) {
        PoliticalPary oldModel = repository.findById(id);

        PoliticalPary model = new PoliticalPary(dto);
        model.setCreatedAt(oldModel.getCreatedAt());
        model.setUpdatedAt(dateUtil.getNowWithZoneIdBr());
        model.setId(id);

        return repository.save(model).toPoliticalParyDTO();
    }

    @Override
    public void delete(Long id) {
        PoliticalPary politicalPary = repository.findById(id);

        repository.detele(politicalPary);
    }

    List<PoliticalPary> filter(Map<String, String> input) {
        
        String fields = null;

        for(Map.Entry<String, String> v : input.entrySet()) {
            if( equalsAndNoEmptry(v, "fields") ) { fields =  v.getValue(); }
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