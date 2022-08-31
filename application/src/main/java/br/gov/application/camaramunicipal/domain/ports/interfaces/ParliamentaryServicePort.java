package br.gov.application.camaramunicipal.domain.ports.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import br.gov.application.camaramunicipal.domain.dtos.ParliamentaryDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.ParliamentarySimpleDTO;

public interface ParliamentaryServicePort {
    List<ParliamentarySimpleDTO> findAll(Map<String, String> inputs);

    Page<ParliamentarySimpleDTO> findAll(Map<String, String> inputs, int offset, int pageSize);

    ParliamentaryDTO findById(Long id);

    ParliamentaryDTO save(ParliamentaryDTO dto);

    ParliamentaryDTO save(ParliamentaryDTO dto, Long id);

    void delete(Long id);
}