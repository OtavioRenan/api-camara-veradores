package br.gov.application.camaramunicipal.infra.adapters.entitys;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import br.gov.application.camaramunicipal.domain.PoliticalPary;

@Entity(name = "politicals_parys")
public class PoliticalParyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "O nome é obrigatório.")
    private String name;

    @NotEmpty(message = "A sigla é obrigatório.")
    private String initials;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public PoliticalParyEntity() {}

    public PoliticalParyEntity(PoliticalPary pary) {
        id = pary.getId();
        name = pary.getName();
        initials = pary.getInitials();
        createdAt = pary.getCreatedAt();
        updatedAt = pary.getUpdatedAt();
    }

    public PoliticalPary toPoliticalPary() {
        return new PoliticalPary(this);
    }

    public Long getId() { return id; }

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