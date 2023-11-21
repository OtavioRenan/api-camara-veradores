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

import br.gov.application.camaramunicipal.domain.Commission;
import br.gov.application.camaramunicipal.domain.adapters.CommissionServiceImp;
import br.gov.application.camaramunicipal.domain.dtos.CommissionDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.CommissionSimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.CommissionServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.CommissionRepositoryPort;

@ExtendWith(SpringExtension.class)
class CommissionServiceImpTest {
    @TestConfiguration
    static class CommissionServiceImpTestConfig {
        @Bean
        CommissionServicePort commissionService(CommissionRepositoryPort repository) { return new CommissionServiceImp(repository); }
    }

    @Autowired
    private CommissionServicePort service;

    @MockBean
    private CommissionRepositoryPort repository;

    private static final Timestamp NOW = new Timestamp(System.currentTimeMillis());

    private static final Commission COMMISSION =
        new Commission(1L, "Constituição e Justiça", "Permanente", NOW, NOW);

        private static final int OFF_SET = 0;

        private static final int PAGE_SIZE = 5;

    @BeforeEach
    void setup() {
        when(repository.findAllWithFilters(null)).thenReturn(mockCommissions());
        when(repository.findAllWithFilters(null, PageRequest.of(OFF_SET, PAGE_SIZE))).thenReturn(mockPageCommissions());
        when(repository.findById(COMMISSION.getId())).thenReturn(COMMISSION);
        when(repository.save(any(Commission.class))).thenReturn(COMMISSION);
        spy(COMMISSION);
    }

    @Test
    void success_when_acess_findAll() {
        List<CommissionSimpleDTO> actual = service.findAll(makeFilter("", ""));
        
        List<CommissionSimpleDTO> expected = new ArrayList<>();
        expected.add(COMMISSION.toCommissionSimpleDTO());

        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getName(), actual.get(0).getName());
        assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
    }

    @Test
    void success_when_acess_findAll_with_pageable() {
        Page<CommissionSimpleDTO> actual = service.findAll(makeFilter("", ""), OFF_SET, PAGE_SIZE);
        
        Page<CommissionSimpleDTO> expected = mockPageCommissions().map(Commission::toCommissionSimpleDTO);

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
        CommissionDTO actual = service.findById(COMMISSION.getId());
        
        CommissionDTO expected = COMMISSION.toCommissionDTO();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    void success_when_acess_save() {
        CommissionDTO model = new CommissionDTO();
        model.setName(COMMISSION.getName());
        model.setDescription(COMMISSION.getDescription());

        CommissionDTO actual = service.save(model);
        
        CommissionDTO expected = COMMISSION.toCommissionDTO();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    void success_when_acess_update() {
        CommissionDTO actual = service.save(COMMISSION.toCommissionDTO());
        
        CommissionDTO expected = COMMISSION.toCommissionDTO();

        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    void success_when_acess_delete() {
        doNothing().when(repository).detele(COMMISSION);

        service.delete(COMMISSION.getId());

        verify(repository, times(1)).detele(COMMISSION);
    }

    private List<Commission> mockCommissions() {
        List<Commission> list = new ArrayList<>();
        list.add(COMMISSION);

        return list;
    }

    private Page<Commission> mockPageCommissions() {
        return new PageImpl<>(mockCommissions(), PageRequest.of(OFF_SET, PAGE_SIZE), mockCommissions().size());
    }

    private Map<String, String> makeFilter(String param, String value) {
        Map<String, String> inputs = new HashMap<>();

        if(!(param.isBlank() || param.isEmpty())) { inputs.put(param, value); }

        return inputs;
    }
}
