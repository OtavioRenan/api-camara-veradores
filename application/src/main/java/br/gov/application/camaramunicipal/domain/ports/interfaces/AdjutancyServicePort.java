package br.gov.application.camaramunicipal.domain.ports.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import br.gov.application.camaramunicipal.domain.dtos.AdjutancyDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.AdjutancySimpleDTO;

public interface AdjutancyServicePort {
    List<AdjutancySimpleDTO> findAll(Map<String, String> inputs);

    Page<AdjutancySimpleDTO> findAll(Map<String, String> inputs, int offset, int pageSize);

    AdjutancyDTO findById(Long id);

    AdjutancyDTO save(AdjutancyDTO dto);

    AdjutancyDTO save(AdjutancyDTO dto, Long id);

    void delete(Long id);
}