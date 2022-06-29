package br.gov.application.camaramunicipal.domain;

import java.sql.Timestamp;

import br.gov.application.camaramunicipal.domain.dtos.CommissionDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.CommissionSimpleDTO;

public class Commission {
    private Long id;
    
    private String name;

    private String description;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Commission() {}

    public Commission(Long id, String name, String description, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public CommissionDTO toCommissionDTO() {
        return new CommissionDTO(id, name, description, createdAt, updatedAt);
    }

    public CommissionSimpleDTO toCommissionSimpleDTO() {
        return new CommissionSimpleDTO(id, name, description);
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Timestamp getCreatedAt() { return createdAt; }

    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}