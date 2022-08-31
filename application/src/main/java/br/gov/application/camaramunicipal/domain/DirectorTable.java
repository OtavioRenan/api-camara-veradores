package br.gov.application.camaramunicipal.domain;

import java.sql.Timestamp;

import br.gov.application.camaramunicipal.domain.dtos.DirectorTableDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.DirectorTableSimpleDTO;

public class DirectorTable {

    private Long id;
    
    private Long legislatureId;

    private Long adjutancyId;

    private Long parliamentaryId;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public DirectorTable() {}

    public DirectorTable(Long id, Long legislatureId, Long adjutancyId, Long parliamentaryId, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.legislatureId = legislatureId;
        this.adjutancyId = adjutancyId;
        this.parliamentaryId = parliamentaryId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public DirectorTable(DirectorTableDTO dto) {
        id = dto.getId();
        legislatureId = dto.getLegislatureId();
        adjutancyId = dto.getAdjutancyId();
        parliamentaryId = dto.getLegislatureId();
    }

    public DirectorTableDTO toDirectorTableDTO() { return new DirectorTableDTO(id, legislatureId, adjutancyId, parliamentaryId, createdAt, updatedAt); }

    public DirectorTableSimpleDTO toDirectorTableSimpleDTO() { return new DirectorTableSimpleDTO(id, legislatureId, adjutancyId, parliamentaryId); }

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
}
