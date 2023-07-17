package br.gov.application.camaramunicipal.domain.adapters;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.gov.application.camaramunicipal.domain.Parliamentary;
import br.gov.application.camaramunicipal.domain.dtos.ParliamentaryDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.ParliamentarySimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.ParliamentaryServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.ParliamentaryRepositoryPort;
import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;
import br.gov.application.camaramunicipal.utils.FiltersUtil;

public class ParliamentaryServiceImp implements ParliamentaryServicePort {
    private final ParliamentaryRepositoryPort repository;

    private static final FactoryFormatDateUtil dateUtil = new FactoryFormatDateUtil();

    private static final FiltersUtil filterUtil = new FiltersUtil();

    public ParliamentaryServiceImp(ParliamentaryRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<ParliamentarySimpleDTO> findAll(Map<String, String> inputs) {
        Long politicalParyId = null;
        Long legislatureId = null;
        Date birth = null;
        String fields = null;

        for (Map.Entry<String, String> v : inputs.entrySet()) {
            if (equalsAndNoEmptry(v, "politicalParyId")) {
                politicalParyId = Long.valueOf(v.getValue());
            } else if (equalsAndNoEmptry(v, "legislatureId")) {
                legislatureId = Long.valueOf(v.getValue());
            } else if (equalsAndNoEmptry(v, "birth")) {
                birth = Date.valueOf(v.getValue());
            } else if (equalsAndNoEmptry(v, "fields")) {
                fields = v.getValue();
            }
        }

        return toParliamentarySimpleDTO(repository.findAllWithFilters(politicalParyId, legislatureId, birth, fields));
    }

    @Override
    public Page<ParliamentarySimpleDTO> findAll(Map<String, String> inputs, int offset, int pageSize) {
        Long politicalParyId = null;
        Long legislatureId = null;
        Date birth = null;
        String fields = null;

        for (Map.Entry<String, String> v : inputs.entrySet()) {
            if (equalsAndNoEmptry(v, "politicalParyId")) {
                politicalParyId = Long.valueOf(v.getValue());
            } else if (equalsAndNoEmptry(v, "legislatureId")) {
                legislatureId = Long.valueOf(v.getValue());
            } else if (equalsAndNoEmptry(v, "birth")) {
                birth = Date.valueOf(v.getValue());
            } else if (equalsAndNoEmptry(v, "fields")) {
                fields = v.getValue();
            }
        }

        return repository
                .findAllWithFilters(politicalParyId, legislatureId, birth, fields, PageRequest.of(offset, pageSize))
                .map(this::toParliamentarySimpleDTO);
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

    private boolean equalsAndNoEmptry(Map.Entry<String, String> map, String column) {
        return filterUtil.equalsAndNoEmptry(map, column);
    }

    private ParliamentarySimpleDTO toParliamentarySimpleDTO(Parliamentary parliamentary) {
        return parliamentary.toParliamentarySimpleDTO();
    }

    private List<ParliamentarySimpleDTO> toParliamentarySimpleDTO(List<Parliamentary> list) {
        return list.stream().map(this::toParliamentarySimpleDTO).collect(Collectors.toList());
    }
}
