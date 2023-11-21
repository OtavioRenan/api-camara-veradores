package br.gov.application.camaramunicipal.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.sql.Date;
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

import br.gov.application.camaramunicipal.domain.Legislature;
import br.gov.application.camaramunicipal.domain.adapters.LegislatureServiceImp;
import br.gov.application.camaramunicipal.domain.dtos.LegislatureDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.LegisLatureSimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.LegislatureServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.LegislatureRepositoryPort;

@ExtendWith(SpringExtension.class)
class LegislatureServiceImpTest {
    @TestConfiguration
    static class LegislatureServiceImpTestConfig {
        @Bean
        LegislatureServicePort legislatureService(LegislatureRepositoryPort repository) { return new LegislatureServiceImp(repository); }
    }

    @Autowired
    private LegislatureServicePort service;

    @MockBean
    private LegislatureRepositoryPort repository;

    private static final Timestamp NOW = new Timestamp(System.currentTimeMillis());

    private static final Legislature LEGISLATURE =
        new Legislature(1L, "Primeira Legislatura",
            Date.valueOf("2018-01-01"), Date.valueOf("2022-01-01"), NOW, NOW);

    @BeforeEach
    void setup() {
        when(repository.findAllWithFilters(null, null, null)).thenReturn(legislatures());
        when(repository.findById(LEGISLATURE.getId())).thenReturn(LEGISLATURE);
        when(repository.save(any(Legislature.class))).thenReturn(LEGISLATURE);
        spy(LEGISLATURE);
    }

    @Test
    void success_when_acess_findAll() {
        List<LegisLatureSimpleDTO> actual = service.findAll(makeFilter("", ""));
        
        List<LegisLatureSimpleDTO> expected = new ArrayList<>();
        expected.add(LEGISLATURE.toLegislatureSimple());

        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
    }

    @Test
    void success_when_acess_findById() {
        LegislatureDTO actual = service.findById(LEGISLATURE.getId());

        LegislatureDTO expected = LEGISLATURE.toLegislature();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getDateStart(), actual.getDateStart());
        assertEquals(expected.getDateEnd(), actual.getDateEnd());
    }

    @Test
    void success_when_acess_save() {
        LegislatureDTO model = new LegislatureDTO();
        model.setDescription(LEGISLATURE.getDescription());
        model.setDateStart(LEGISLATURE.getDateStart());
        model.setDateEnd(LEGISLATURE.getDateEnd());

        LegislatureDTO actual = service.save(model);
        
        LegislatureDTO expected = LEGISLATURE.toLegislature();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getDateStart(), actual.getDateStart());
        assertEquals(expected.getDateEnd(), actual.getDateEnd());
    }

    @Test
    void success_when_acess_update() {
        LegislatureDTO actual = service.save(LEGISLATURE.toLegislature());
        
        LegislatureDTO expected = LEGISLATURE.toLegislature();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getDescription(), actual.getDescription());
        assertEquals(expected.getDateStart(), actual.getDateStart());
        assertEquals(expected.getDateEnd(), actual.getDateEnd());
    }

    @Test
    void success_when_acess_delete() {
        doNothing().when(repository).detele(LEGISLATURE);

        service.delete(LEGISLATURE.getId());

        verify(repository, times(1)).detele(LEGISLATURE);
    }

    private List<Legislature> legislatures() {
        List<Legislature> list = new ArrayList<>();
        list.add(LEGISLATURE);
        
        return list;
    }

    private Map<String, String> makeFilter(String param, String value) {
        Map<String, String> inputs = new HashMap<>();

        if(!(param.isBlank() || param.isEmpty())) { inputs.put(param, value); }

        return inputs;
    }
}