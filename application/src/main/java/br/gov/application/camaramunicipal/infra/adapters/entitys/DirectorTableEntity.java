package br.gov.application.camaramunicipal.infra.adapters.entitys;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotNull;

import br.gov.application.camaramunicipal.domain.DirectorTable;

@Entity(name = "directors_tables")
public class DirectorTableEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "A legislatura é obrigatória.")
    @Column(name = "legislature_id")
    private Long legislatureId;

    @NotNull(message = "O cargo é obrigatório.")
    @Column(name = "adjutancy_id")
    private Long adjutancyId;

    @NotNull(message = "O parlamentar é obrigatório.")
    @Column(name = "parliamentary_id")
    private Long parliamentaryId;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public DirectorTableEntity() {}

    public DirectorTableEntity(DirectorTable directorTable) {
        id = directorTable.getId();
        legislatureId = directorTable.getLegislatureId();
        adjutancyId = directorTable.getAdjutancyId();
        parliamentaryId = directorTable.getParliamentaryId();
        createdAt = directorTable.getCreatedAt();
        updatedAt = directorTable.getUpdatedAt();
    }

    public DirectorTable toDirectorTable() {
        return new DirectorTable(id, legislatureId, adjutancyId, parliamentaryId, createdAt, updatedAt);
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
}