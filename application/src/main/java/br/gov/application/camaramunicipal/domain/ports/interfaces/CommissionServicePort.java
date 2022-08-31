package br.gov.application.camaramunicipal.domain.ports.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import br.gov.application.camaramunicipal.domain.dtos.CommissionDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.CommissionSimpleDTO;

public interface CommissionServicePort {
    List<CommissionSimpleDTO> findAll(Map<String, String> inputs);

    Page<CommissionSimpleDTO> findAll(Map<String, String> inputs, int offset, int pageSize);

    CommissionDTO findById(Long id);

    CommissionDTO save(CommissionDTO dto);

    CommissionDTO save(CommissionDTO dto, Long id);

    void delete(Long id);
}