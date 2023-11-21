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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import br.gov.application.camaramunicipal.domain.Adjutancy;
import br.gov.application.camaramunicipal.domain.adapters.AdjutancyServiceImp;
import br.gov.application.camaramunicipal.domain.dtos.AdjutancyDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.AdjutancySimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.AdjutancyServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.AdjutancyRepositoryPort;

@ExtendWith(SpringExtension.class)
class AdjutancyServiceImpTest {

    @TestConfiguration
    static class AdjutancyServiceImpTestCofig {
        @Bean
        AdjutancyServicePort adjutancyService(AdjutancyRepositoryPort repository) { return new AdjutancyServiceImp(repository); }
    }

    @Autowired
    private AdjutancyServicePort service;
    
    @MockBean
    private AdjutancyRepositoryPort repository;

    private static final Timestamp NOW = new Timestamp(System.currentTimeMillis());

    private static final Adjutancy ADJUTANCY =
        new Adjutancy(1L, "Presidente", "Presidente de Comissão", NOW, NOW);

        private static final int OFF_SET = 0;

        private static final int PAGE_SIZE = 5;

    @BeforeEach
    public void setup() {
        when(repository.findAllWithFilters(null)).thenReturn(mockAdjutancies());
        when(repository.findAllWithFilters(null, PageRequest.of(OFF_SET, PAGE_SIZE))).thenReturn(mockPageAdjutancies());
        when(repository.findById(ADJUTANCY.getId())).thenReturn(ADJUTANCY);
        when(repository.save(any(Adjutancy.class))).thenReturn(ADJUTANCY);
        spy(ADJUTANCY);
    }

    @Test
    void success_when_acess_findAll() {
        List<AdjutancySimpleDTO> actual = service.findAll(makeFilter("", ""));
        
        List<AdjutancySimpleDTO> expected = new ArrayList<>();
        expected.add(ADJUTANCY.toAdjutancySimpleDTO());

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getName(), actual.get(0).getName());
        assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
    }

    @Test
    void success_when_acess_findAll_with_pageable() {
        Page<AdjutancySimpleDTO> actual = service.findAll(makeFilter("", ""), OFF_SET, PAGE_SIZE);
        
        Page<AdjutancySimpleDTO> expected = mockPageAdjutancies().map(Adjutancy::toAdjutancySimpleDTO);

        assertEquals(expected.getNumber(), actual.getNumber());
        assertEquals(expected.getNumberOfElements(), actual.getNumberOfElements());
        assertEquals(expected.getSize(), actual.getSize());
        assertEquals(expected.getSort(), actual.getSort());
        assertEquals(expected.getTotalElements(), actual.getTotalElements());
        assertEquals(expected.getTotalPages(), actual.getTotalPages());

        assertEquals(expected.getContent().get(0).getId(), actual.getContent().get(0).getId());
        assertEquals(expected.getContent().get(0).getName(), actual.getContent().get(0).getName());
        assertEquals(expected.getContent().get(0).getDescription(), actual.getContent().get(0).getDescription());
    }

    @Test
    void success_when_acess_findById() {
        AdjutancyDTO actual = service.findById(ADJUTANCY.getId());
        
        AdjutancyDTO expected = ADJUTANCY.toAdjutancyDTO();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    void success_when_acess_save() {
        AdjutancyDTO model = new AdjutancyDTO();
        model.setName(ADJUTANCY.getName());
        model.setDescription(ADJUTANCY.getDescription());

        AdjutancyDTO actual = service.save(model);
        
        AdjutancyDTO expected = ADJUTANCY.toAdjutancyDTO();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    void success_when_acess_update() {
        AdjutancyDTO actual = service.save(ADJUTANCY.toAdjutancyDTO());
        
        AdjutancyDTO expected = ADJUTANCY.toAdjutancyDTO();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    void success_when_acess_delete() {
        doNothing().when(repository).detele(ADJUTANCY);

        service.delete(ADJUTANCY.getId());

        verify(repository, times(1)).detele(ADJUTANCY);
    }

    private List<Adjutancy> mockAdjutancies() {
        List<Adjutancy> models = new ArrayList<>();
        models.add(ADJUTANCY);
        
        return models;
    }

    private Page<Adjutancy> mockPageAdjutancies() {
        return new PageImpl<>(mockAdjutancies(), PageRequest.of(OFF_SET, PAGE_SIZE), mockAdjutancies().size());
    }

    private Map<String, String> makeFilter(String param, String value) {
        Map<String, String> inputs = new HashMap<>();

        if(!(param.isBlank() || param.isEmpty())) { inputs.put(param, value); }

        return inputs;
    }
}