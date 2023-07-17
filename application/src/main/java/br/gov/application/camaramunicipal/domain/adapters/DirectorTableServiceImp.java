package br.gov.application.camaramunicipal.domain.adapters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.gov.application.camaramunicipal.domain.DirectorTable;
import br.gov.application.camaramunicipal.domain.dtos.DirectorTableDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.DirectorTableSimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.DirectorTableServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.DirectorTableRepositoryPort;
import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;
import br.gov.application.camaramunicipal.utils.FiltersUtil;

public class DirectorTableServiceImp implements DirectorTableServicePort {

    private final DirectorTableRepositoryPort repository;

    private static final FactoryFormatDateUtil dateUtil = new FactoryFormatDateUtil();

    private static final FiltersUtil filterUtil = new FiltersUtil();

    public DirectorTableServiceImp(DirectorTableRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<DirectorTableSimpleDTO> findAll(Map<String, String> inputs) {
        Long legislatureId = null;
        Long adjutancyId = null;
        Long parliamentaryId = null;

        for (Map.Entry<String, String> v : inputs.entrySet()) {
            if (equalsAndNoEmptry(v, "legislatureId")) {
                legislatureId = Long.valueOf(v.getValue());
            }
            if (equalsAndNoEmptry(v, "adjutancyId")) {
                adjutancyId = Long.valueOf(v.getValue());
            }
            if (equalsAndNoEmptry(v, "parliamentaryId")) {
                parliamentaryId = Long.valueOf(v.getValue());
            }
        }

        return toDirectorTableSimpleDTO(repository.findAllWithFilters(legislatureId, adjutancyId, parliamentaryId));
    }

    @Override
    public Page<DirectorTableSimpleDTO> findAll(Map<String, String> inputs, int offSet, int pageSize) {
        Long legislatureId = null;
        Long adjutancyId = null;
        Long parliamentaryId = null;

        for (Map.Entry<String, String> v : inputs.entrySet()) {
            if (equalsAndNoEmptry(v, "legislatureId")) {
                legislatureId = Long.valueOf(v.getValue());
            }
            if (equalsAndNoEmptry(v, "adjutancyId")) {
                adjutancyId = Long.valueOf(v.getValue());
            }
            if (equalsAndNoEmptry(v, "parliamentaryId")) {
                parliamentaryId = Long.valueOf(v.getValue());
            }
        }

        return repository
                .findAllWithFilters(legislatureId, adjutancyId, parliamentaryId, PageRequest.of(offSet, pageSize))
                .map(this::toDirectorTableSimpleDTO);
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

    private boolean equalsAndNoEmptry(Map.Entry<String, String> map, String column) {
        return filterUtil.equalsAndNoEmptry(map, column);
    }

    private DirectorTableSimpleDTO toDirectorTableSimpleDTO(DirectorTable directorTable) {
        return directorTable.toDirectorTableSimpleDTO();
    }

    private List<DirectorTableSimpleDTO> toDirectorTableSimpleDTO(List<DirectorTable> list) {
        return list.stream().map(this::toDirectorTableSimpleDTO).collect(Collectors.toList());
    }
}