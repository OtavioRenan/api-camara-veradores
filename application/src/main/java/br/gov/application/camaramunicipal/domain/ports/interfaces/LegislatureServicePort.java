package br.gov.application.camaramunicipal.domain.ports.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import br.gov.application.camaramunicipal.domain.dtos.LegislatureDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.LegisLatureSimpleDTO;

public interface LegislatureServicePort {
    List<LegisLatureSimpleDTO> findAll(Map<String, String> inputs);

    Page<LegisLatureSimpleDTO> findAll(Map<String, String> inputs, int offset, int pageSize);

    LegislatureDTO findById(Long id);

    LegislatureDTO save(LegislatureDTO dto);

    LegislatureDTO save(LegislatureDTO dto, Long id);

    void delete(Long id);
}