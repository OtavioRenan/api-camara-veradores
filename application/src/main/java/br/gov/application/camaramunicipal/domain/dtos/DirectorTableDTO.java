package br.gov.application.camaramunicipal.domain.dtos;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;

public class DirectorTableDTO {
    
    private Long id;
    
    @NotNull(message = "A legislatura é obrigatória.")
    @NotEmpty(message = "A legislatura é obrigatória.")
    private Long legislatureId;

    @NotNull(message = "O cargo é obrigatório.")
    @NotEmpty(message = "O cargo é obrigatório.")
    private Long adjutancyId;

    @NotNull(message = "Parlamentar é obrigatório (a).")
    @NotEmpty(message = "Parlamentar é obrigatório (a).")
    private Long parliamentaryId;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String createdAtBr;

    private String updatedAtBr;

    private String formatDate(Timestamp date) { return new FactoryFormatDateUtil().formatDateBr(date); }

    public DirectorTableDTO() {}

    public DirectorTableDTO(Long id, Long legislatureId, Long adjutancyId, Long parliamentaryId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.legislatureId = legislatureId;
        this.adjutancyId = adjutancyId;
        this.parliamentaryId = parliamentaryId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        this.createdAtBr = formatDate( createdAt );
        this.updatedAtBr = formatDate( updatedAt );
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getLegislatureId() { return legislatureId; }

    public void setLegislatureId(Long legislatureId) { this.legislatureId = legislatureId; }

    public Long getAdjutancyId() { return adjutancyId; }

    public void setAdjutancyId(Long adjutancyId) { this.adjutancyId = adjutancyId; }

    public Long getParliamentaryId() { return parliamentaryId; }

    public void setParliamentaryId(Long parliamentaryId) { this.parliamentaryId = parliamentaryId; }

    public Timestamp getCreatedAt() { return createdAt; }

    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }

    public String getCreatedAtBr() { return createdAtBr; }

    public void setCreatedAtBr(String createdAtBr) { this.createdAtBr = createdAtBr; }
 
    public String getUpdatedAtBr() { return updatedAtBr; }

    public void setUpdatedAtBr(String updatedAtBr) { this.updatedAtBr = updatedAtBr; }
}
