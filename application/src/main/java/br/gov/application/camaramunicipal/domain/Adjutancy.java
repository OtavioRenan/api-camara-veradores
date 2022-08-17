package br.gov.application.camaramunicipal.domain;

import java.sql.Timestamp;

import br.gov.application.camaramunicipal.domain.dtos.AdjutancyDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.AdjutancySimpleDTO;

public class Adjutancy {    
    private Long id;
    
    private String name;

    private String description;
    
    private Timestamp createdAt;
    
    private Timestamp updatedAt;

    public Adjutancy() {}

    public Adjutancy(Long id, String name, String description, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public Adjutancy(AdjutancyDTO dto) {
        this.id = dto.getId();
        this.name = dto.getName();
        this.description = dto.getDescription();
    }

    public AdjutancyDTO toAdjutancyDTO() { return new AdjutancyDTO(id, name, description, createdAt, updatedAt); }

    public AdjutancySimpleDTO toAdjutancySimpleDTO() { return new AdjutancySimpleDTO(id, name, description); }

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