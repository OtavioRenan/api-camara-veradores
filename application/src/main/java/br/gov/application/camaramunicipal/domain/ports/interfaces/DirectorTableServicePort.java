package br.gov.application.camaramunicipal.domain.ports.interfaces;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;

import br.gov.application.camaramunicipal.domain.dtos.DirectorTableDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.DirectorTableSimpleDTO;

public interface DirectorTableServicePort {
    List<DirectorTableSimpleDTO> findAll(Map<String, String> inputs);

    Page<DirectorTableSimpleDTO> findAll(Map<String, String> inputs, int offset, int pageSize);

    DirectorTableDTO findById(Long id);

    DirectorTableDTO save(DirectorTableDTO dto);

    DirectorTableDTO save(DirectorTableDTO dto, Long id);

    void delete(Long id);
}
