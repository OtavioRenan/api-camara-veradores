package br.gov.application.camaramunicipal.domain.ports.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import br.gov.application.camaramunicipal.domain.dtos.PoliticalParyDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.PoliticalParySimpleDTO;

public interface PoliticalParyServicePort {
    List<PoliticalParySimpleDTO> findAll(Map<String, String> inputs);

    Page<PoliticalParySimpleDTO> findAll(Map<String, String> inputs, int offset, int pageSize);

    PoliticalParyDTO findById(Long id);

    PoliticalParyDTO save(PoliticalParyDTO dto);

    PoliticalParyDTO save(PoliticalParyDTO dto, Long id);

    void delete(Long id);
}