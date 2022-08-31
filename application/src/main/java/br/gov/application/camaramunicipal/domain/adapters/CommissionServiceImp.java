package br.gov.application.camaramunicipal.domain.adapters;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import br.gov.application.camaramunicipal.domain.Commission;
import br.gov.application.camaramunicipal.domain.dtos.CommissionDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.CommissionSimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.CommissionServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.CommissionRepositoryPort;
import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;

public class CommissionServiceImp implements CommissionServicePort {

    private final CommissionRepositoryPort repository;

    private static final FactoryFormatDateUtil dateUtil = new FactoryFormatDateUtil();

    public CommissionServiceImp(CommissionRepositoryPort repository) {
        this.repository = repository;
    }

    @Override
    public List<CommissionSimpleDTO> findAll(Map<String, String> inputs) {
        List<Commission> models = repository.findAll();

        models = filters(inputs, models);

        return models.stream().map( Commission::toCommissionSimpleDTO ).collect(Collectors.toList());
    }

    @Override
    public Page<CommissionSimpleDTO> findAll(Map<String, String> inputs, int offSet, int pageSize) {
        if(inputs.size() > 0) {
            List<CommissionSimpleDTO> models = findAll(inputs);
            return new PageImpl<>(models, PageRequest.of(offSet, pageSize), models.size());
        }

        Page<Commission> pages = repository.findAll(offSet, pageSize);
        return pages.map( Commission::toCommissionSimpleDTO );
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

    private List<Commission> filters(Map<String, String> inputs, List<Commission> models) {

        String fields = isNullOrEmptry(inputs.get("fields"));

        if(!Objects.isNull(fields)) { models = filterByFields(models, fields); }

        return models;
    }

    private List<Commission> filterByFields(List<Commission> list, String fileds) {
        return list.stream().filter( comission -> filterByFields(comission, fileds) ).collect(Collectors.toList());
    }

    private boolean filterByFields(Commission commission, String fields) {
        return commission.getName().contains(fields) || commission.getDescription().contains(fields);
    }

    private String isNullOrEmptry(String str) { return (Objects.isNull(str) || str.isEmpty()) ? null : str; }
}