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

import br.gov.application.camaramunicipal.domain.Adjutancy;
import br.gov.application.camaramunicipal.domain.adapters.AdjutancyServiceImp;
import br.gov.application.camaramunicipal.domain.dtos.simples.AdjutancySimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.AdjutancyServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.AdjutancyRepositoryPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class AdjutancyServiceTest {

    @TestConfiguration
    static class AdjutancyServiceTestCofig {
        @Bean
        AdjutancyServicePort adjutancyService(AdjutancyRepositoryPort repository) { return new AdjutancyServiceImp(repository); }
    }

    @Autowired
    private AdjutancyServicePort service;
    
    @MockBean
    private AdjutancyRepositoryPort repository;

    private static final Long ID = 1L;

    private static final Adjutancy ADJUTANCY = new Adjutancy(ID, "Presidente", "Presidente de Comissão",  new Timestamp(System.currentTimeMillis()),  new Timestamp(System.currentTimeMillis()));

    @BeforeEach
    public void setup() {
        when(repository.findAll()).thenReturn(mockAdjutancies());
        when(repository.findById(ID)).thenReturn(ADJUTANCY);
    }

    @Test
    public void successExpected_whenAcessFindAll() {
        List<AdjutancySimpleDTO> actual = service.findAll(makeFilter("", ""));
        
        List<AdjutancySimpleDTO> expected = new ArrayList<>();
        expected.add(ADJUTANCY.toAdjutancySimpleDTO());
     
        assertEquals(expected.size(), actual.size());
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getName(), actual.get(0).getName());
        assertEquals(expected.get(0).getDescription(), actual.get(0).getDescription());
    }

    private List<Adjutancy> mockAdjutancies() {
        List<Adjutancy> models = new ArrayList<>();
        models.add(ADJUTANCY);
        
        return models;
    }

    private Map<String, String> makeFilter(String param, String value) {
        Map<String, String> inputs = new HashMap<>();

        if(!(param.isBlank() || param.isEmpty())) { inputs.put(param, value); }

        return inputs;
    }
}