package br.gov.application.camaramunicipal.domain.adapters;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
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

    private static final FiltersUtil filterUtil = new FiltersUtil();

    public CommissionServiceImp(CommissionRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<CommissionSimpleDTO> findAll(Map<String, String> inputs) {
        String fields = null;

        for (Map.Entry<String, String> v : inputs.entrySet()) {
            if (equalsAndNoEmptry(v, "fields")) {
                fields = v.getValue();
            }
        }

        return toCommissionSimpleDTO(repository.findAllWithFilters(fields));
    }

    @Override
    public Page<CommissionSimpleDTO> findAll(Map<String, String> inputs, int offSet, int pageSize) {
        String fields = null;

        for (Map.Entry<String, String> v : inputs.entrySet()) {
            if (equalsAndNoEmptry(v, "fields")) {
                fields = v.getValue();
            }
        }

        return repository.findAllWithFilters(fields, PageRequest.of(offSet, pageSize)).map(this::toCommissionSimpleDTO);
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

    private boolean equalsAndNoEmptry(Map.Entry<String, String> map, String column) {
        return filterUtil.equalsAndNoEmptry(map, column);
    }

    private CommissionSimpleDTO toCommissionSimpleDTO(Commission commission) {
        return commission.toCommissionSimpleDTO();
    }

    private List<CommissionSimpleDTO> toCommissionSimpleDTO(List<Commission> list) {
        return list.stream().map(this::toCommissionSimpleDTO).collect(Collectors.toList());
    }
}