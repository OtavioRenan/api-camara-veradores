package br.gov.application.camaramunicipal.domain.adapters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.gov.application.camaramunicipal.domain.Commission;
import br.gov.application.camaramunicipal.domain.dtos.CommissionDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.CommissionSimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.CommissionServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.CommissionRepositoryPort;
import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;
import br.gov.application.camaramunicipal.utils.FiltersUtil;

public class CommissionServiceImp implements CommissionServicePort {

    private final CommissionRepositoryPort repository;

    private static final FactoryFormatDateUtil dateUtil = new FactoryFormatDateUtil();

    private static final FiltersUtil filterUtil =  new FiltersUtil();

    public CommissionServiceImp(CommissionRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<CommissionSimpleDTO> findAll(Map<String, String> inputs) {
        List<Commission> models = repository.findAll();

        if( filterEmptry(inputs) ) {
            models.addAll( repository.findAllLimit(200) );
        } else {
            models.addAll( filter(inputs) );
        }

        return models.stream().map( Commission::toCommissionSimpleDTO ).collect(Collectors.toList());
    }

    @Override
    public Page<CommissionSimpleDTO> findAll(Map<String, String> inputs, int offSet, int pageSize) {
        List<CommissionSimpleDTO> models = findAll(inputs);

        return new PageImpl<>(models, PageRequest.of(offSet, pageSize), models.size()); 
    }

    @Override
    public CommissionDTO findById(Long id) {
        Commission model = repository.findById(id);
        
        return model.toCommissionDTO();
    }

    @Override
    public CommissionDTO save(CommissionDTO dto) {
        Commission model = new Commission(dto);
        model.setCreatedAt(dateUtil.getNowWithZoneIdBr());

        return repository.save(model).toCommissionDTO();
    }

    @Override
    public CommissionDTO save(CommissionDTO dto, Long id) {
        Commission oldModel = repository.findById(id);
        
        Commission model = new Commission(dto);
        model.setCreatedAt(oldModel.getCreatedAt());
        model.setUpdatedAt(dateUtil.getNowWithZoneIdBr());
        model.setId(id);

        return repository.save(model).toCommissionDTO();
    }

    @Override
    public void delete(Long id) {
        Commission commission = repository.findById(id);

        repository.detele(commission);
    }

    List<Commission> filter(Map<String, String> input) {
        
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