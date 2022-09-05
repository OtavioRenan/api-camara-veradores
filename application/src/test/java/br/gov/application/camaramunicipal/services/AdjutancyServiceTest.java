package br.gov.application.camaramunicipal.services;

import java.sql.Timestamp;
import java.util.logging.Logger;

import org.junit.jupiter.api.Test;

import br.gov.application.camaramunicipal.domain.dtos.AdjutancyDTO;
import br.gov.application.camaramunicipal.domain.ports.interfaces.AdjutancyServicePort;
import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;

public class AdjutancyServiceTest {

    private final AdjutancyServicePort service;

    private static final Logger LOGGER = Logger.getLogger(AdjutancyServiceTest.class.getName());

    public AdjutancyServiceTest(AdjutancyServicePort service) { this.service = service; }

    @Test
    public void find_all_test()
    {
        try {
            LOGGER.info(mockDTO().getName());
        } catch (Exception e) {
            LOGGER.warning(e.getMessage());
        }
    }

    private AdjutancyDTO mockDTO()
    {
        return new AdjutancyDTO(1L, "Presidente", "Presidente de Comissão", mockTimestamp(), mockTimestamp());
    }

    private Timestamp mockTimestamp() {
        return new FactoryFormatDateUtil().getNowWithZoneIdBr();
    }
}