package br.gov.application.camaramunicipal.domain;

import java.sql.Timestamp;

import br.gov.application.camaramunicipal.domain.dtos.PoliticalParyDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.PoliticalParySimpleDTO;
import br.gov.application.camaramunicipal.infra.adapters.entitys.PoliticalParyEntity;

public class PoliticalPary {
    private Long id;
    
    private String name;

    private String initials;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Long getId() { return id; }

    public PoliticalPary() {}

    public PoliticalPary(Long id, String name, String initials, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.initials = initials;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public PoliticalPary(PoliticalParyEntity pary) {
        id = pary.getId();
        name = pary.getName();
        initials = pary.getInitials();
        createdAt = pary.getCreatedAt();
        updatedAt = pary.getUpdatedAt();
    }

    public PoliticalPary(PoliticalParyDTO pary) {
        id = pary.getId();
        name = pary.getName();
        initials = pary.getInitials();
        createdAt = pary.getCreatedAt();
        updatedAt = pary.getUpdatedAt();
    }

    public PoliticalParyDTO toPoliticalParyDTO() {
        return new PoliticalParyDTO(id, name, initials, createdAt, updatedAt);
    }

    public PoliticalParySimpleDTO toPoliticalParySimpleDTO() {
        return new PoliticalParySimpleDTO(id, name, initials);
    }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getInitials() { return initials; }

    public void setInitials(String initials) { this.initials = initials; }

    public Timestamp getCreatedAt() { return createdAt; }

    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}