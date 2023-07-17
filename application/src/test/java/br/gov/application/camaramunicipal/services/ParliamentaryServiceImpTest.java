package br.gov.application.camaramunicipal.services;

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

import br.gov.application.camaramunicipal.domain.Parliamentary;
import br.gov.application.camaramunicipal.domain.adapters.ParliamentaryServiceImp;
import br.gov.application.camaramunicipal.domain.dtos.ParliamentaryDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.ParliamentarySimpleDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.ParliamentaryServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.ParliamentaryRepositoryPort;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class ParliamentaryServiceImpTest {
    @TestConfiguration
    static class ParliamentaryServiceImpTestConfig {
        @Bean
        ParliamentaryServicePort parliamentaryService(ParliamentaryRepositoryPort repository) { return new ParliamentaryServiceImp(repository); }
    }

    @Autowired
    private ParliamentaryServicePort service;

    @MockBean
    private ParliamentaryRepositoryPort repository;

    private static final Timestamp NOW = new Timestamp(System.currentTimeMillis());

    private static final Parliamentary PARLIAMENTARY = 
        new Parliamentary(1L, 1L, 1L, "Fulano de Tal",
            "Fulaninho", "fulaninho-de-tal@email", "(71)90000-0000",
            Date.valueOf("1990-01-01"), NOW, NOW);

    @BeforeEach
    public void setup() {
        when(repository.findAll()).thenReturn(parliamentaries());
        when(repository.findById(PARLIAMENTARY.getId())).thenReturn(PARLIAMENTARY);
        when(repository.save(any(Parliamentary.class))).thenReturn(PARLIAMENTARY);
        spy(PARLIAMENTARY);
    }

    @Test
    public void success_when_acess_findAll() {
        List<ParliamentarySimpleDTO> actual = service.findAll(makeFilter("", ""));
        
        List<ParliamentarySimpleDTO> expected = new ArrayList<>();
        expected.add(PARLIAMENTARY.toParliamentarySimpleDTO());
     
        assertEquals(expected.get(0).getId(), actual.get(0).getId());
        assertEquals(expected.get(0).getSocialName(), actual.get(0).getSocialName());
        assertEquals(expected.get(0).getEmail(), actual.get(0).getEmail());
        assertEquals(expected.get(0).getNumberPhone(), actual.get(0).getNumberPhone());
        assertEquals(expected.get(0).getBirthBr(), actual.get(0).getBirthBr());
    }

    @Test
    public void success_when_acess_FindById() {
        ParliamentaryDTO actual = service.findById(PARLIAMENTARY.getId());

        ParliamentaryDTO expected = PARLIAMENTARY.toParliamentaryDTO();
     
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getSocialName(), actual.getSocialName());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getNumberPhone(), actual.getNumberPhone());
        assertEquals(expected.getBirthBr(), actual.getBirthBr());
    }

    @Test
    public void success_when_acess_save() {
        ParliamentaryDTO model = new ParliamentaryDTO(PARLIAMENTARY);

        ParliamentaryDTO actual = service.save(model);
        
        ParliamentaryDTO expected = PARLIAMENTARY.toParliamentaryDTO();
     
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getSocialName(), actual.getSocialName());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getNumberPhone(), actual.getNumberPhone());
        assertEquals(expected.getBirthBr(), actual.getBirthBr());
    }

    @Test
    public void success_when_acess_update() {
        ParliamentaryDTO actual = service.save(PARLIAMENTARY.toParliamentaryDTO());
        
        ParliamentaryDTO expected = PARLIAMENTARY.toParliamentaryDTO();
     
        assertEquals(expected.getId(), actual.getId());
        assertEquals(expected.getName(), actual.getName());
        assertEquals(expected.getSocialName(), actual.getSocialName());
        assertEquals(expected.getEmail(), actual.getEmail());
        assertEquals(expected.getNumberPhone(), actual.getNumberPhone());
        assertEquals(expected.getBirthBr(), actual.getBirthBr());
    }

    @Test
    public void success_when_acess_delete() {
        service.delete(PARLIAMENTARY.getId());
    }

    private List<Parliamentary> parliamentaries() {
        List<Parliamentary> list = new ArrayList<>();
        list.add(PARLIAMENTARY);

        return list;
    }

    private Map<String, String> makeFilter(String param, String value) {
        Map<String, String> inputs = new HashMap<>();

        if(!(param.isBlank() || param.isEmpty())) { inputs.put(param, value); }

        return inputs;
    }
}
