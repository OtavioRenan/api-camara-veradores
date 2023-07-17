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

import br.gov.application.camaramunicipal.domain.Commission;
import br.gov.application.camaramunicipal.domain.adapters.CommissionServiceImp;
import br.gov.application.camaramunicipal.domain.dtos.CommissionDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.CommissionSimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.CommissionServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.CommissionRepositoryPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class CommissionServiceImpTest {
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


    @BeforeEach
    public void setup() {
        when(repository.findAll()).thenReturn(mockCommissions());
        when(repository.findById(COMMISSION.getId())).thenReturn(COMMISSION);
        when(repository.save(any(Commission.class))).thenReturn(COMMISSION);
        spy(COMMISSION);
    }

    @Test
    public void success_when_acess_findAll() {
        List<CommissionSimpleDTO> actual = service.findAll(makeFilter("", ""));
        
        List<CommissionSimpleDTO> expected = new ArrayList<>();
        expected.add(COMMISSION.toCommissionSimpleDTO());
     
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getName(), actual.get(0).getName());
        assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
    }

    @Test
    public void success_when_acess_FindById() {
        CommissionDTO actual = service.findById(COMMISSION.getId());
        
        CommissionDTO expected = COMMISSION.toCommissionDTO();
     
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    public void success_when_acess_save() {
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
    public void success_when_acess_update() {
        CommissionDTO actual = service.save(COMMISSION.toCommissionDTO());
        
        CommissionDTO expected = COMMISSION.toCommissionDTO();
     
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getDescription(), actual.getDescription());
    }

    @Test
    public void success_when_acess_delete() {
        service.delete(COMMISSION.getId());
    }

    private List<Commission> mockCommissions() {
        List<Commission> list = new ArrayList<>();
        list.add(COMMISSION);

        return list;
    }

    private Map<String, String> makeFilter(String param, String value) {
        Map<String, String> inputs = new HashMap<>();

        if(!(param.isBlank() || param.isEmpty())) { inputs.put(param, value); }

        return inputs;
    }
}
