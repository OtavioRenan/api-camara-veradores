package br.gov.application.camaramunicipal.services;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.gov.application.camaramunicipal.domain.DirectorTable;
import br.gov.application.camaramunicipal.domain.adapters.DirectorTableServiceImp;
import br.gov.application.camaramunicipal.domain.dtos.DirectorTableDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.DirectorTableSimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.DirectorTableServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.DirectorTableRepositoryPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
class DirectorTableServiceImpTest {
    @TestConfiguration
    static class DirectorTableServiceImpTestConfig {
        @Bean
        DirectorTableServicePort directorTableService(DirectorTableRepositoryPort repository) { return new DirectorTableServiceImp(repository); }
    }

    @Autowired
    private DirectorTableServicePort service;

    @MockBean
    private DirectorTableRepositoryPort repository;

    private static final Timestamp NOW = new Timestamp(System.currentTimeMillis());

    private static final DirectorTable DIRECTOR_TABLE =
        new DirectorTable(1L, 1L, 1L, 1L, NOW, NOW);

    @BeforeEach
    void setup() {
        when(repository.findAllWithFilters(null, null, null)).thenReturn(directorTables());
        when(repository.findById(DIRECTOR_TABLE.getId())).thenReturn(DIRECTOR_TABLE);
        when(repository.save(any(DirectorTable.class))).thenReturn(DIRECTOR_TABLE);
        spy(DIRECTOR_TABLE);
    }

    @Test
    void success_when_acess_findAll() {
        List<DirectorTableSimpleDTO> actual = service.findAll(makeFilter("", ""));
        
        List<DirectorTableSimpleDTO> expected = new ArrayList<>();
        expected.add(DIRECTOR_TABLE.toDirectorTableSimpleDTO());
     
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getAdjutancyId(), actual.get(0).getAdjutancyId());
        assertEquals(expected.get(0).getLegislatureId(), actual.get(0).getLegislatureId());
        assertEquals(expected.get(0).getParliamentaryId(), actual.get(0).getParliamentaryId());
    }

    @Test
    void success_when_acess_findById() {
        DirectorTableDTO actual = service.findById(DIRECTOR_TABLE.getId());
        
        DirectorTableDTO expected = DIRECTOR_TABLE.toDirectorTableDTO();
     
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAdjutancyId(), actual.getAdjutancyId());
        assertEquals(expected.getLegislatureId(), actual.getLegislatureId());
        assertEquals(expected.getParliamentaryId(), actual.getParliamentaryId());
    }

    @Test
    void success_when_acess_save() {
        DirectorTableDTO model = new DirectorTableDTO();
        model.setAdjutancyId(DIRECTOR_TABLE.getAdjutancyId());
        model.setLegislatureId(DIRECTOR_TABLE.getLegislatureId());
        model.setParliamentaryId(DIRECTOR_TABLE.getParliamentaryId());

        DirectorTableDTO actual = service.save(model);
        
        DirectorTableDTO expected = DIRECTOR_TABLE.toDirectorTableDTO();
     
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAdjutancyId(), actual.getAdjutancyId());
        assertEquals(expected.getLegislatureId(), actual.getLegislatureId());
        assertEquals(expected.getParliamentaryId(), actual.getParliamentaryId());
    }

    @Test
    void success_when_acess_update() {
        DirectorTableDTO actual = service.save(DIRECTOR_TABLE.toDirectorTableDTO());
        
        DirectorTableDTO expected = DIRECTOR_TABLE.toDirectorTableDTO();
     
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getAdjutancyId(), actual.getAdjutancyId());
        assertEquals(expected.getLegislatureId(), actual.getLegislatureId());
        assertEquals(expected.getParliamentaryId(), actual.getParliamentaryId());
    }

    @Test
    void success_when_acess_delete() {
        doNothing().when(repository).detele(DIRECTOR_TABLE);

        service.delete(DIRECTOR_TABLE.getId());

        verify(repository, times(1)).detele(DIRECTOR_TABLE);
    }

    private List<DirectorTable> directorTables() {
        List<DirectorTable> models = new ArrayList<>();
        models.add(DIRECTOR_TABLE);

        return models;
    }

    private Map<String, String> makeFilter(String param, String value) {
        Map<String, String> inputs = new HashMap<>();

        if(!(param.isBlank() || param.isEmpty())) { inputs.put(param, value); }

        return inputs;
    }
}
