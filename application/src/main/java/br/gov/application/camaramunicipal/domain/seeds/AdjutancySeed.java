package br.gov.application.camaramunicipal.domain.seeds;

import java.util.ArrayList;
import java.util.List;

import br.gov.application.camaramunicipal.domain.dtos.AdjutancyDTO;

public class AdjutancySeed {
    private List<AdjutancyDTO> adjutancys = new ArrayList<>();

    private AdjutancyDTO factoryAdjutancyDTO(String name, String description) {
        AdjutancyDTO dto = new AdjutancyDTO();
        dto.setName(name);
        dto.setDescription(description);
        return dto;
    }

    public AdjutancySeed() {
        this.adjutancys.add( factoryAdjutancyDTO("Presidente", "Presidente da casa ou de comissão") );
        this.adjutancys.add( factoryAdjutancyDTO("Vice-Presidente", "Vice-Presidente da casa ou de comissão") );
        this.adjutancys.add( factoryAdjutancyDTO("1º Suplente", "1º Suplente") );
        this.adjutancys.add( factoryAdjutancyDTO("2º Suplente", "2º Suplente") );
        this.adjutancys.add( factoryAdjutancyDTO("3º Suplente", "3º Suplente") );
        this.adjutancys.add( factoryAdjutancyDTO("Menbro", "Membro") );
        this.adjutancys.add( factoryAdjutancyDTO("Parlamentar", "Parlamentar") );
    }

    public List<AdjutancyDTO> getAdjutancys() { return adjutancys; }
}