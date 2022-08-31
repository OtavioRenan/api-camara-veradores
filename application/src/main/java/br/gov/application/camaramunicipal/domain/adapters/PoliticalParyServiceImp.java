package br.gov.application.camaramunicipal.domain.adapters;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.gov.application.camaramunicipal.domain.PoliticalPary;
import br.gov.application.camaramunicipal.domain.dtos.PoliticalParyDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.PoliticalParySimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.PoliticalParyServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.PoliticalParyRepositoryPort;
import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;

public class PoliticalParyServiceImp implements PoliticalParyServicePort {
    private final PoliticalParyRepositoryPort repository;

    private static final FactoryFormatDateUtil dateUtil = new FactoryFormatDateUtil();

    public PoliticalParyServiceImp(PoliticalParyRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<PoliticalParySimpleDTO> findAll(Map<String, String> inputs) {
        List<PoliticalPary> models = repository.findAll();

        models = filters(inputs, models);
        
        return models.stream().map( PoliticalPary::toPoliticalParySimpleDTO ).collect(Collectors.toList());
    }

    @Override
    public Page<PoliticalParySimpleDTO> findAll(Map<String, String> inputs, int offset, int pageSize) {
        if(inputs.size() > 0) {
            List<PoliticalParySimpleDTO> models = findAll(inputs);
            return new PageImpl<>(models, PageRequest.of(offset, pageSize), models.size());
        }

        Page<PoliticalPary> pages = repository.findAll(offset, pageSize);
        return pages.map( PoliticalPary::toPoliticalParySimpleDTO );
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

    private List<PoliticalPary> filters(Map<String, String> inputs, List<PoliticalPary> models) {

        String name = isPresentReturnString(inputs.get("name"));
        String initials = isPresentReturnString(inputs.get("initials"));

        if(!Objects.isNull(name)) { models = filterByName(models, name); }
        if(!Objects.isNull(initials)) { models = filterByInitials(models, initials); }

        return models;
    }

    private List<PoliticalPary> filterByName(List<PoliticalPary> list, String name) {
        return list.stream().filter( politicalPary -> politicalPary.getName().contains(name) ).collect(Collectors.toList());
    }

    private List<PoliticalPary> filterByInitials(List<PoliticalPary> list, String initials) {
        return list.stream().filter( politicalPary -> politicalPary.getInitials().contains(initials) ).collect(Collectors.toList());
    }

    private String isPresentReturnString(String str) { return (Objects.isNull(str) || str.isEmpty()) ? null : str; }
}