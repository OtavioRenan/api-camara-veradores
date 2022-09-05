package br.gov.application.camaramunicipal.infra.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import br.gov.application.camaramunicipal.domain.adapters.AdjutancyServiceImp;
import br.gov.application.camaramunicipal.domain.adapters.CommissionServiceImp;
import br.gov.application.camaramunicipal.domain.adapters.DirectorTableServiceImp;
import br.gov.application.camaramunicipal.domain.adapters.LegislatureServiceImp;
import br.gov.application.camaramunicipal.domain.adapters.ParliamentaryServiceImp;
import br.gov.application.camaramunicipal.domain.adapters.PoliticalParyServiceImp;
import br.gov.application.camaramunicipal.domain.ports.interfaces.AdjutancyServicePort;
import br.gov.application.camaramunicipal.domain.ports.interfaces.CommissionServicePort;
import br.gov.application.camaramunicipal.domain.ports.interfaces.DirectorTableServicePort;
import br.gov.application.camaramunicipal.domain.ports.interfaces.LegislatureServicePort;
import br.gov.application.camaramunicipal.domain.ports.interfaces.ParliamentaryServicePort;
import br.gov.application.camaramunicipal.domain.ports.interfaces.PoliticalParyServicePort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.AdjutancyRepositoryPort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.CommissionRepositoryPort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.DirectorTableRepositoryPort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.LegislatureRepositoryPort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.ParliamentaryRepositoryPort;
import br.gov.application.camaramunicipal.domain.ports.repositorys.PoliticalParyRepositoryPort;

@Configuration
public class BeanConfig {

    @Bean
    AdjutancyServicePort adjutancyService(AdjutancyRepositoryPort repository) { return new AdjutancyServiceImp(repository); }

    @Bean
    CommissionServicePort commissionService(CommissionRepositoryPort repository) { return new CommissionServiceImp(repository); }

    @Bean
    DirectorTableServicePort directorTableService(DirectorTableRepositoryPort repository) { return new DirectorTableServiceImp(repository); }

    @Bean
    LegislatureServicePort legislatureService(LegislatureRepositoryPort repository) { return new LegislatureServiceImp(repository); }

    @Bean
    ParliamentaryServicePort parliamentaryService(ParliamentaryRepositoryPort repository) { return new ParliamentaryServiceImp(repository); }

    @Bean
    PoliticalParyServicePort politicalParyService(PoliticalParyRepositoryPort repository) { return new PoliticalParyServiceImp(repository); }
}
