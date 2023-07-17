package br.gov.application.camaramunicipal.domain.adapters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.gov.application.camaramunicipal.domain.PoliticalPary;
import br.gov.application.camaramunicipal.domain.dtos.PoliticalParyDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.PoliticalParySimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.PoliticalParyServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.PoliticalParyRepositoryPort;
import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;
import br.gov.application.camaramunicipal.utils.FiltersUtil;

public class PoliticalParyServiceImp implements PoliticalParyServicePort {
    private final PoliticalParyRepositoryPort repository;

    private static final FactoryFormatDateUtil dateUtil = new FactoryFormatDateUtil();

    private static final FiltersUtil filterUtil = new FiltersUtil();

    public PoliticalParyServiceImp(PoliticalParyRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<PoliticalParySimpleDTO> findAll(Map<String, String> inputs) {
        String fields = null;

        for (Map.Entry<String, String> v : inputs.entrySet()) {
            if (equalsAndNoEmptry(v, "fields")) {
                fields = v.getValue();
            }
        }

        return toPoliticalParySimpleDTO(repository.findAllWithFilters(fields));
    }

    @Override
    public Page<PoliticalParySimpleDTO> findAll(Map<String, String> inputs, int offset, int pageSize) {
        String fields = null;

        for (Map.Entry<String, String> v : inputs.entrySet()) {
            if (equalsAndNoEmptry(v, "fields")) {
                fields = v.getValue();
            }
        }

        return repository.findAllWithFilters(fields, PageRequest.of(offset, pageSize))
                .map(this::toPoliticalParySimpleDTO);
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

    private boolean equalsAndNoEmptry(Map.Entry<String, String> map, String column) {
        return filterUtil.equalsAndNoEmptry(map, column);
    }

    private PoliticalParySimpleDTO toPoliticalParySimpleDTO(PoliticalPary politicalPary) {
        return politicalPary.toPoliticalParySimpleDTO();
    }

    private List<PoliticalParySimpleDTO> toPoliticalParySimpleDTO(List<PoliticalPary> list) {
        return list.stream().map(this::toPoliticalParySimpleDTO).collect(Collectors.toList());
    }
}