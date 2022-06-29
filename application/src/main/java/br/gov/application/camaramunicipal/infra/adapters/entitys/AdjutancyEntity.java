package br.gov.application.camaramunicipal.infra.adapters.entitys;

import java.sql.Timestamp;

import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.NotEmpty;

import br.gov.application.camaramunicipal.domain.Adjutancy;

@Entity(name = "adjutancys")
public class AdjutancyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "O nome é obrigatório.")
    private String name;

    @NotEmpty(message = "A descrição é obrigatório.")
    private String description;
    
    @Column(name = "created_at")
    private Timestamp createdAt;
    
    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public AdjutancyEntity() {}

    public AdjutancyEntity(Adjutancy adjutancy) {
        id = adjutancy.getId();
        name = adjutancy.getName();
        description = adjutancy.getDescription();
        createdAt = adjutancy.getCreatedAt();
        updatedAt = adjutancy.getUpdatedAt();
    }

    public Adjutancy toAdjutancy() {
        return new Adjutancy(id, name, description, createdAt, updatedAt);
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