package br.gov.application.camaramunicipal.domain.dtos;

import java.sql.Timestamp;

import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;

public class PoliticalParyDTO {
    private Long id;
    
    private String name;

    private String initials;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String createdAtBr;

    private String updatedAtBr;

    private String formatDate(Timestamp date) {
        return new FactoryFormatDateUtil().formatDateBr(date);
    }

    public PoliticalParyDTO() {}

    public PoliticalParyDTO(Long id, String name, String initials, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.name = name;
        this.initials = initials;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        this.createdAtBr = formatDate( createdAt );
        this.updatedAtBr = formatDate( updatedAt );
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

    public String getCreatedAtBr() { return createdAtBr; }

    public void setCreatedAtBr(String createdAtBr) { this.createdAtBr = createdAtBr; }

    public String getUpdatedAtBr() { return updatedAtBr; }

    public void setUpdatedAtBr(String updatedAtBr) { this.updatedAtBr = updatedAtBr; }
}