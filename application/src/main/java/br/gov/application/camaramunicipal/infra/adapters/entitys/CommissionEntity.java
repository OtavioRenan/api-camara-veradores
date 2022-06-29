package br.gov.application.camaramunicipal.infra.adapters.entitys;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import br.gov.application.camaramunicipal.domain.Commission;

@Entity(name = "commissions")
public class CommissionEntity {
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

    public CommissionEntity() {}

    public CommissionEntity(Commission commission) {
        id = commission.getId();
        name = commission.getName();
        description = commission.getDescription();
        createdAt = commission.getCreatedAt();
        updatedAt = commission.getUpdatedAt();
    }

    public Commission toCommission() {
        return new Commission(id, name, description, createdAt, updatedAt);
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