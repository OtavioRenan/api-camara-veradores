package br.gov.application.camaramunicipal.domain.adapters;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;

import br.gov.application.camaramunicipal.domain.DirectorTable;
import br.gov.application.camaramunicipal.domain.dtos.DirectorTableDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.DirectorTableSimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.DirectorTableServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.DirectorTableRepositoryPort;
import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;
import br.gov.application.camaramunicipal.utils.FiltersUtil;
import br.gov.application.camaramunicipal.utils.PageableUtil;

public class DirectorTableServiceImp implements DirectorTableServicePort {

    private final DirectorTableRepositoryPort repository;

    private static final FactoryFormatDateUtil dateUtil = new FactoryFormatDateUtil();

    private static final FiltersUtil filterUtil =  new FiltersUtil();

    public DirectorTableServiceImp(DirectorTableRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<DirectorTableSimpleDTO> findAll(Map<String, String> inputs) {
        List<DirectorTable> models = new ArrayList<>();

        if( filterEmptry(inputs) ) {
            models.addAll( repository.findAllLimit(200) );
        } else {
            models.addAll( filter(inputs) );
        }

        return models.stream().map( DirectorTable::toDirectorTableSimpleDTO ).collect(Collectors.toList());
    }

    @Override
    public Page<DirectorTableSimpleDTO> findAll(Map<String, String> inputs, int offSet, int pageSize) {
        List<DirectorTableSimpleDTO> models = findAll(inputs);

        return new PageableUtil().pageable(models, offSet, pageSize);
    }

    @Override
    public DirectorTableDTO findById(Long id) {
        DirectorTable model = repository.findById(id);
        
        return model.toDirectorTableDTO();
    }

    @Override
    public DirectorTableDTO save(DirectorTableDTO dto) {
        DirectorTable model = new DirectorTable(dto);
        model.setCreatedAt(dateUtil.getNowWithZoneIdBr());

        return repository.save(model).toDirectorTableDTO();
    }

    @Override
    public DirectorTableDTO save(DirectorTableDTO dto, Long id) {
        DirectorTable oldModel = repository.findById(id);
        
        DirectorTable model = new DirectorTable(dto);
        model.setCreatedAt(oldModel.getCreatedAt());
        model.setUpdatedAt(dateUtil.getNowWithZoneIdBr());
        model.setId(id);

        return repository.save(model).toDirectorTableDTO();
    }

    @Override
    public void delete(Long id) {
        DirectorTable directorTable = repository.findById(id);

        repository.detele(directorTable);
    }

    List<DirectorTable> filter(Map<String, String> input) {
        
        Long legislatureId = null;
        Long adjutancyId = null;
        Long parliamentaryId = null;

        for(Map.Entry<String, String> v : input.entrySet()) {
            if( equalsAndNoEmptry(v, "legislatureId") ) { legislatureId = Long.valueOf( v.getValue() ); }
            if( equalsAndNoEmptry(v, "adjutancyId") ) { adjutancyId = Long.valueOf( v.getValue() ); }
            if( equalsAndNoEmptry(v, "parliamentaryId") ) { parliamentaryId = Long.valueOf( v.getValue() ); }
        }

        return repository.findAllWithFilters(legislatureId, adjutancyId, parliamentaryId);
    }

    private boolean equalsAndNoEmptry(Map.Entry<String, String> map, String column) {
        return filterUtil.equalsAndNoEmptry(map, column);
    }

    private boolean filterEmptry(Map<String, String> map) {
        return filterUtil.filterEmptry(map);
    }
}