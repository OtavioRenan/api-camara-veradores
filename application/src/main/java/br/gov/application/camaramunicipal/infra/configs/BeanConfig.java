package br.gov.application.camaramunicipal.infra.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.gov.application.camaramunicipal.domain.adapters.AdjutancyServiceImp;
import br.gov.application.camaramunicipal.domain.ports.interfaces.AdjutancyServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.AdjutancyRepositoryPort;

@Configuration
public class BeanConfig {

    @Bean
    AdjutancyServicePort adjutancyService(AdjutancyRepositoryPort repository) { return new AdjutancyServiceImp(repository); }
}
