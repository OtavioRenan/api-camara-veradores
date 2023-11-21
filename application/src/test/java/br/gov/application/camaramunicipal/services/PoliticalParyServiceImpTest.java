package br.gov.application.camaramunicipal.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

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

import br.gov.application.camaramunicipal.domain.PoliticalPary;
import br.gov.application.camaramunicipal.domain.adapters.PoliticalParyServiceImp;
import br.gov.application.camaramunicipal.domain.dtos.PoliticalParyDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.PoliticalParySimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.PoliticalParyServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.PoliticalParyRepositoryPort;


@ExtendWith(SpringExtension.class)
class PoliticalParyServiceImpTest {
    @TestConfiguration
    static class PoliticalParyServiceImpTestConfig {
        @Bean
        PoliticalParyServicePort politicalParyService(PoliticalParyRepositoryPort repository) { return new PoliticalParyServiceImp(repository); }
    }

    @Autowired
    private PoliticalParyServicePort service;

    @MockBean
    private PoliticalParyRepositoryPort repository;

    private static final Timestamp NOW = new Timestamp(System.currentTimeMillis());

    private static final PoliticalPary PARY = new PoliticalPary(1L, "Teste", "TT", NOW, NOW);

    @BeforeEach
    void setup() {
        when(repository.findAllWithFilters(null)).thenReturn(politicalParies());
        when(repository.findById(PARY.getId())).thenReturn(PARY);
        when(repository.save(any(PoliticalPary.class))).thenReturn(PARY);
        spy(PARY);
    }

    @Test
    void success_when_acess_findAll() {
        List<PoliticalParySimpleDTO> actual = service.findAll(makeFilter("", ""));
        
        List<PoliticalParySimpleDTO> expected = new ArrayList<>();
        expected.add(PARY.toPoliticalParySimpleDTO());

        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getName(), actual.get(0).getName());
        assertEquals(expected.get(0).getInitials(), actual.get(0).getInitials());
    }

    @Test
    void success_when_acess_findById() {
        PoliticalParyDTO actual = service.findById(PARY.getId());

        PoliticalParyDTO expected = PARY.toPoliticalParyDTO();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getInitials(), actual.getInitials());
    }

    @Test
    void success_when_acess_save() {
        PoliticalParyDTO model = new PoliticalParyDTO();
        model.setName(PARY.getName());
        model.setInitials(PARY.getInitials());

        PoliticalParyDTO actual = service.save(model);
        
        PoliticalParyDTO expected = PARY.toPoliticalParyDTO();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getInitials(), actual.getInitials());
    }

    @Test
    void success_when_acess_update() {
        PoliticalParyDTO actual = service.save(PARY.toPoliticalParyDTO());
        
        PoliticalParyDTO expected = PARY.toPoliticalParyDTO();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getInitials(), actual.getInitials());
    }

    @Test
    void success_when_acess_delete() {
        doNothing().when(repository).detele(PARY);

        service.delete(PARY.getId());

        verify(repository, times(1)).detele(PARY);
    }

    private List<PoliticalPary> politicalParies() {
        List<PoliticalPary> list = new ArrayList<>();
        list.add(PARY);
        
        return list;
    }

    private Map<String, String> makeFilter(String param, String value) {
        Map<String, String> inputs = new HashMap<>();

        if(!(param.isBlank() || param.isEmpty())) { inputs.put(param, value); }

        return inputs;
    }
}
