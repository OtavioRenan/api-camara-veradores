package br.gov.application.camaramunicipal.domain.adapters;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.gov.application.camaramunicipal.domain.Legislature;
import br.gov.application.camaramunicipal.domain.dtos.LegislatureDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.LegisLatureSimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.LegislatureServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.LegislatureRepositoryPort;
import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;

public class LegislatureServiceImp implements LegislatureServicePort {
    private final LegislatureRepositoryPort repository;

    private static final FactoryFormatDateUtil dateUtil = new FactoryFormatDateUtil();

    public LegislatureServiceImp(LegislatureRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<LegisLatureSimpleDTO> findAll(Map<String, String> inputs) {
        List<Legislature> models = repository.findAll();

        models = filters(inputs, models);
        
        return models.stream().map( Legislature::toLegislatureSimple ).collect(Collectors.toList());
    }

    @Override
    public Page<LegisLatureSimpleDTO> findAll(Map<String, String> inputs, int offset, int pageSize) {
        if(inputs.size() > 0) {
            List<LegisLatureSimpleDTO> models = findAll(inputs);
            return new PageImpl<>(models, PageRequest.of(offset, pageSize), models.size());
        }

        Page<Legislature> pages = repository.findAll(offset, pageSize);
        return pages.map( Legislature::toLegislatureSimple );
    }

    @Override
    public LegislatureDTO findById(Long id) {
        Legislature model = repository.findById(id);
        
        return model.toLegislature();
    }

    @Override
    public LegislatureDTO save(LegislatureDTO dto) {
        Legislature model = new Legislature(dto);
        model.setCreatedAt(dateUtil.getNowWithZoneIdBr());

        return repository.save(model).toLegislature();
    }

    @Override
    public LegislatureDTO save(LegislatureDTO dto, Long id) {
        Legislature oldModel = repository.findById(id);

        Legislature model = new Legislature(dto);
        model.setCreatedAt(oldModel.getCreatedAt());
        model.setUpdatedAt(dateUtil.getNowWithZoneIdBr());
        model.setId(id);

        return repository.save(model).toLegislature();
    }

    @Override
    public void delete(Long id) {
        Legislature legislature = repository.findById(id);

        repository.detele(legislature);
    }
    
    private List<Legislature> filters(Map<String, String> inputs, List<Legislature> models) {

        String description = isPresentReturnString(inputs.get("description"));
        Date dateStart = isPresentReturnDate(inputs.get("dateStart"));
        Date dateEnd = isPresentReturnDate(inputs.get("dateEnd"));

        if(!Objects.isNull(description)) { models = filterByDescription(models, description); }
        if(!Objects.isNull(dateStart)) { models = filterByDateStart(models, dateStart); }
        if(!Objects.isNull(dateEnd)) { models = filterByDateEnd(models, dateEnd); }

        return models;
    }

    private List<Legislature> filterByDescription(List<Legislature> list, String description) {
        return list.stream().filter( legislature -> legislature.getDescription().contains(description) ).collect(Collectors.toList());
    }

    private List<Legislature> filterByDateStart(List<Legislature> list, Date dateStart) {
        return list.stream().filter( legislature -> legislature.getDateStart().after(dateStart) ).collect(Collectors.toList());
    }

    private List<Legislature> filterByDateEnd(List<Legislature> list, Date dateEnd) {
        return list.stream().filter( legislature -> legislature.getDateEnd().before(dateEnd) ).collect(Collectors.toList());
    }

    private String isPresentReturnString(String str) { return (Objects.isNull(str) || str.isEmpty()) ? null : str; }

    private Date isPresentReturnDate(String str) { return (Objects.isNull(str) || str.isEmpty()) ? null : Date.valueOf(str); }
}