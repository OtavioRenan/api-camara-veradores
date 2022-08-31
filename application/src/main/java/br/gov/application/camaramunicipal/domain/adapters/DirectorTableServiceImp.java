package br.gov.application.camaramunicipal.domain.adapters;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.gov.application.camaramunicipal.domain.DirectorTable;
import br.gov.application.camaramunicipal.domain.dtos.DirectorTableDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.DirectorTableSimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.DirectorTableServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.DirectorTableRepositoryPort;
import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;

public class DirectorTableServiceImp implements DirectorTableServicePort {

    private final DirectorTableRepositoryPort repository;

    private static final FactoryFormatDateUtil dateUtil = new FactoryFormatDateUtil();

    public DirectorTableServiceImp(DirectorTableRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<DirectorTableSimpleDTO> findAll(Map<String, String> inputs) {
        List<DirectorTable> models = repository.findAll();

        models = filters(inputs, models);
        
        return models.stream().map( DirectorTable::toDirectorTableSimpleDTO ).collect(Collectors.toList());
    }

    @Override
    public Page<DirectorTableSimpleDTO> findAll(Map<String, String> inputs, int offSet, int pageSize) {
        if(inputs.size() > 0) {
            List<DirectorTableSimpleDTO> models = findAll(inputs);
            return new PageImpl<>(models, PageRequest.of(offSet, pageSize), models.size());
        }

        Page<DirectorTable> pages = repository.findAll(offSet, pageSize);
        return pages.map( DirectorTable::toDirectorTableSimpleDTO );
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

    private List<DirectorTable> filters(Map<String, String> inputs, List<DirectorTable> models) {

        Long legislatureId = isPresentReturnLong(inputs.get("legislatureId"));
        Long adjutancyId = isPresentReturnLong(inputs.get("adjutancyId"));
        Long parliamentaryId = isPresentReturnLong(inputs.get("parliamentaryId"));

        if(!Objects.isNull(legislatureId)) { models = filterByLegislatureId(models, legislatureId); }
        if(!Objects.isNull(adjutancyId)) { models = filterByAdjutancyId(models, adjutancyId); }
        if(!Objects.isNull(parliamentaryId)) { models = filterByParliamentaryId(models, parliamentaryId); }

        return models;
    }

    private List<DirectorTable> filterByLegislatureId(List<DirectorTable> list, Long legislatureId) {
        return list.stream().filter( directorTable -> directorTable.getLegislatureId().equals(legislatureId) ).collect(Collectors.toList());
    }

    private List<DirectorTable> filterByAdjutancyId(List<DirectorTable> list, Long adjutancyId) {
        return list.stream().filter( directorTable -> directorTable.getAdjutancyId().equals(adjutancyId) ).collect(Collectors.toList());
    }

    private List<DirectorTable> filterByParliamentaryId(List<DirectorTable> list, Long parliamentaryId) {
        return list.stream().filter( directorTable -> directorTable.getParliamentaryId().equals(parliamentaryId) ).collect(Collectors.toList());
    }

    private Long isPresentReturnLong(String str) { return (Objects.isNull(str) || str.isEmpty()) ? null : Long.valueOf(str); }
}