package br.gov.application.camaramunicipal.domain.seeds;

import java.util.ArrayList;
import java.util.List;

import br.gov.application.camaramunicipal.domain.dtos.CommissionDTO;

public class CommissionSeed {
    private List<CommissionDTO> commissions = new ArrayList<>();

    private CommissionDTO factoryCommissionDTO(String name, String description) {
        CommissionDTO dto = new CommissionDTO();
        dto.setName(name);
        dto.setDescription(description);
        return dto;
    }

    public CommissionSeed() {
        this.commissions.add( factoryCommissionDTO("CCJ", "Comissão de constituição e justiça") );
        this.commissions.add( factoryCommissionDTO("Comissão econômica", "Comissão econômica") );
        this.commissions.add( factoryCommissionDTO("Comissão de direitos humanos", "Comissão de direitos humanos") );
    }

    public List<CommissionDTO> getCommissins() { return commissions; } 
}