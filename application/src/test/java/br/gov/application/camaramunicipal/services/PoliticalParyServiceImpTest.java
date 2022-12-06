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

import br.gov.application.camaramunicipal.domain.PoliticalPary;
import br.gov.application.camaramunicipal.domain.adapters.PoliticalParyServiceImp;
import br.gov.application.camaramunicipal.domain.dtos.PoliticalParyDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.PoliticalParySimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.PoliticalParyServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.PoliticalParyRepositoryPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
public class PoliticalParyServiceImpTest {
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
    public void setup() {
        when(repository.findAll()).thenReturn(politicalParies());
        when(repository.findAllLimit(200)).thenReturn(politicalParies());
        when(repository.findById(PARY.getId())).thenReturn(PARY);
        when(repository.save(any(PoliticalPary.class))).thenReturn(PARY);
        spy(PARY);
    }

    @Test
    public void success_when_acess_findAll() {
        List<PoliticalParySimpleDTO> actual = service.findAll(makeFilter("", ""));
        
        List<PoliticalParySimpleDTO> expected = new ArrayList<>();
        expected.add(PARY.toPoliticalParySimpleDTO());
     
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getName(), actual.get(0).getName());
        assertEquals(expected.get(0).getInitials(), actual.get(0).getInitials());
    }

    @Test
    public void success_when_acess_FindById() {
        PoliticalParyDTO actual = service.findById(PARY.getId());

        PoliticalParyDTO expected = PARY.toPoliticalParyDTO();
     
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getInitials(), actual.getInitials());
    }

    @Test
    public void success_when_acess_save() {
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
    public void success_when_acess_update() {
        PoliticalParyDTO actual = service.save(PARY.toPoliticalParyDTO());
        
        PoliticalParyDTO expected = PARY.toPoliticalParyDTO();
     
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getInitials(), actual.getInitials());
    }

    @Test
    public void success_when_acess_delete() {
        service.delete(PARY.getId());
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
