package br.gov.application.camaramunicipal.domain.adapters;

import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import br.gov.application.camaramunicipal.domain.Legislature;
import br.gov.application.camaramunicipal.domain.dtos.LegislatureDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.LegisLatureSimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.LegislatureServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.LegislatureRepositoryPort;
import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;
import br.gov.application.camaramunicipal.utils.FiltersUtil;

public class LegislatureServiceImp implements LegislatureServicePort {
    private final LegislatureRepositoryPort repository;

    private static final FactoryFormatDateUtil dateUtil = new FactoryFormatDateUtil();

    private static final FiltersUtil filterUtil = new FiltersUtil();

    public LegislatureServiceImp(LegislatureRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<LegisLatureSimpleDTO> findAll(Map<String, String> inputs) {
        String fields = null;
        Date dateStart = null;
        Date dateEnd = null;

        for (Map.Entry<String, String> v : inputs.entrySet()) {
            if (equalsAndNoEmptry(v, "fields")) {
                fields = v.getValue();
            } else if (equalsAndNoEmptry(v, "dateStart")) {
                dateStart = Date.valueOf(v.getValue());
            } else if (equalsAndNoEmptry(v, "dateEnd")) {
                dateEnd = Date.valueOf(v.getValue());
            }
        }

        return toLegisLatureSimpleDTO(repository.findAllWithFilters(fields, dateStart, dateEnd));
    }

    @Override
    public Page<LegisLatureSimpleDTO> findAll(Map<String, String> inputs, int offset, int pageSize) {
        String fields = null;
        Date dateStart = null;
        Date dateEnd = null;

        for (Map.Entry<String, String> v : inputs.entrySet()) {
            if (equalsAndNoEmptry(v, "fields")) {
                fields = v.getValue();
            } else if (equalsAndNoEmptry(v, "dateStart")) {
                dateStart = Date.valueOf(v.getValue());
            } else if (equalsAndNoEmptry(v, "dateEnd")) {
                dateEnd = Date.valueOf(v.getValue());
            }
        }

        return repository.findAllWithFilters(fields, dateStart, dateEnd, PageRequest.of(offset, pageSize))
                .map(this::toLegisLatureSimpleDTO);
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

    private boolean equalsAndNoEmptry(Map.Entry<String, String> map, String column) {
        return filterUtil.equalsAndNoEmptry(map, column);
    }

    private LegisLatureSimpleDTO toLegisLatureSimpleDTO(Legislature legislature) {
        return legislature.toLegislatureSimple();
    }

    private List<LegisLatureSimpleDTO> toLegisLatureSimpleDTO(List<Legislature> list) {
        return list.stream().map(this::toLegisLatureSimpleDTO).collect(Collectors.toList());
    }
}