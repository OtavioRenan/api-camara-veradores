package br.gov.application.camaramunicipal.domain.dtos;

import java.sql.Timestamp;

import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;

public class CommissionDTO {
    private Long id;
    
    private String name;

    private String description;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String createdAtBr;

    private String updatedAtBr;

    private String formatDate(Timestamp date) {
        return new FactoryFormatDateUtil().formatDateBr(date);
    }

    public CommissionDTO() {}

    public CommissionDTO(Long id, String name, String description, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        this.createdAtBr = formatDate( createdAt );
        this.updatedAtBr = formatDate( updatedAt );
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

    public String getCreatedAtBr() { return createdAtBr; }

    public void setCreatedAtBr(String createdAtBr) { this.createdAtBr = createdAtBr; }

    public String getUpdatedAtBr() { return updatedAtBr; }

    public void setUpdatedAtBr(String updatedAtBr) { this.updatedAtBr = updatedAtBr; }
}
