package br.gov.application.camaramunicipal.domain.adapters;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.gov.application.camaramunicipal.domain.Parliamentary;
import br.gov.application.camaramunicipal.domain.dtos.ParliamentaryDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.ParliamentarySimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.ParliamentaryServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.ParliamentaryRepositoryPort;
import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;

public class ParliamentaryServiceImp implements ParliamentaryServicePort {
    private final ParliamentaryRepositoryPort repository;

    private static final FactoryFormatDateUtil dateUtil = new FactoryFormatDateUtil();

    public ParliamentaryServiceImp(ParliamentaryRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<ParliamentarySimpleDTO> findAll(Map<String, String> inputs) {
        List<Parliamentary> models = repository.findAll();

        models = filters(inputs, models);
        
        return models.stream().map( Parliamentary::toParliamentarySimpleDTO ).collect(Collectors.toList());
    }

    @Override
    public Page<ParliamentarySimpleDTO> findAll(Map<String, String> inputs, int offset, int pageSize) {
        if(inputs.size() > 0) {
            List<ParliamentarySimpleDTO> models = findAll(inputs);
            return new PageImpl<>(models, PageRequest.of(offset, pageSize), models.size());
        }

        Page<Parliamentary> pages = repository.findAll(offset, pageSize);
        return pages.map( Parliamentary::toParliamentarySimpleDTO );
    }

    @Override
    public ParliamentaryDTO findById(Long id) {
        Parliamentary model = repository.findById(id);
        
        return model.toParliamentaryDTO();
    }

    @Override
    public ParliamentaryDTO save(ParliamentaryDTO dto) {
        Parliamentary model = new Parliamentary(dto);
        model.setCreatedAt(dateUtil.getNowWithZoneIdBr());

        return repository.save(model).toParliamentaryDTO();
    }

    @Override
    public ParliamentaryDTO save(ParliamentaryDTO dto, Long id) {
        Parliamentary oldModel = repository.findById(id);

        Parliamentary model = new Parliamentary(dto);
        model.setCreatedAt(oldModel.getCreatedAt());
        model.setUpdatedAt(dateUtil.getNowWithZoneIdBr());
        model.setId(id);

        return repository.save(model).toParliamentaryDTO();
    }

    @Override
    public void delete(Long id) {
        Parliamentary parliamentary = repository.findById(id);

        repository.detele(parliamentary);
    }

    private List<Parliamentary> filters(Map<String, String> inputs, List<Parliamentary> models) {

        Long politicalParyId = isPresentReturnLong(inputs.get("politicalParyId"));
        Long legislatureId = isPresentReturnLong(inputs.get("legislatureId"));
        String name = isPresentReturnString(inputs.get("name"));
        String socialName = isPresentReturnString(inputs.get("socialName"));
        String email = isPresentReturnString(inputs.get("email"));
        String numberPhone = isPresentReturnString(inputs.get("numberPhone"));
        Date birth = isPresentReturnDate(inputs.get("birth"));

        if(!Objects.isNull(politicalParyId)) { models = filterByPoliticalParyId(models, politicalParyId); }
        if(!Objects.isNull(legislatureId)) { models = filterByLegislatureId(models, legislatureId); }
        if(!Objects.isNull(name)) { models = filterByName(models, name); }
        if(!Objects.isNull(socialName)) { models = filterBySocialName(models, socialName); }
        if(!Objects.isNull(email)) { models = filterByEmail(models, email); }
        if(!Objects.isNull(numberPhone)) { models = filterByNumberPhone(models, numberPhone); }
        if(!Objects.isNull(birth)) { models = filterByBirth(models, birth); }

        return models;
    }

    private List<Parliamentary> filterByPoliticalParyId(List<Parliamentary> list, Long politicalParyId) {
        return list.stream().filter( parliamentary -> parliamentary.getPoliticalParyId().equals(politicalParyId) ).collect(Collectors.toList());
    }

    private List<Parliamentary> filterByLegislatureId(List<Parliamentary> list, Long legislatureId) {
        return list.stream().filter( parliamentary -> parliamentary.getLegislatureId().equals(legislatureId) ).collect(Collectors.toList());
    }

    private List<Parliamentary> filterByName(List<Parliamentary> list, String name) {
        return list.stream().filter( parliamentary -> filterByName(parliamentary, name) ).collect(Collectors.toList());
    }

    private List<Parliamentary> filterBySocialName(List<Parliamentary> list, String socialName) {
        return list.stream().filter( parliamentary -> filterByName(parliamentary, socialName) ).collect(Collectors.toList());
    }

    private List<Parliamentary> filterByEmail(List<Parliamentary> list, String email) {
        return list.stream().filter( parliamentary -> parliamentary.getEmail().contains(email) ).collect(Collectors.toList());
    }

    private List<Parliamentary> filterByNumberPhone(List<Parliamentary> list, String numberPhone) {
        return list.stream().filter( parliamentary -> parliamentary.getNumberPhone().contains(numberPhone) ).collect(Collectors.toList());
    }

    private List<Parliamentary> filterByBirth(List<Parliamentary> list, Date birth) {
        return list.stream().filter( parliamentary -> parliamentary.getBirth().equals(birth) ).collect(Collectors.toList());
    }

    private boolean filterByName(Parliamentary parliamentary, String str) {
        return parliamentary.getName().contains(str) || parliamentary.getSocialName().contains(str);
    }

    private String isPresentReturnString(String str) { return (Objects.isNull(str) || str.isEmpty()) ? null : str; }

    private Long isPresentReturnLong(String str) { return (Objects.isNull(str) || str.isEmpty()) ? null : Long.valueOf(str); }

    private Date isPresentReturnDate(String str) { return (Objects.isNull(str) || str.isEmpty()) ? null : Date.valueOf(str); }
}
