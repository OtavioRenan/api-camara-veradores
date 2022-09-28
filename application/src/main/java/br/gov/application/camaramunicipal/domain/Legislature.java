package br.gov.application.camaramunicipal.domain;

import java.sql.Date;
import java.sql.Timestamp;

import br.gov.application.camaramunicipal.domain.dtos.LegislatureDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.LegisLatureSimpleDTO;
import br.gov.application.camaramunicipal.infra.adapters.entitys.LegislatureEntity;

public class Legislature {
    private Long id;
    
    private String description;

    private Date dateStart;

    private Date dateEnd;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Legislature() {}

    public Legislature(LegislatureEntity legislature) {
        this.id = legislature.getId();
        this.description = legislature.getDescription();
        this.dateStart = legislature.getDateStart();
        this.dateEnd = legislature.getDateEnd();
        this.createdAt = legislature.getCreatedAt();
        this.updatedAt = legislature.getUpdatedAt();
    }

    public Legislature(LegislatureDTO legislature) {
        this.id = legislature.getId();
        this.description = legislature.getDescription();
        this.dateStart = legislature.getDateStart();
        this.dateEnd = legislature.getDateEnd();
        this.createdAt = legislature.getCreatedAt();
        this.updatedAt = legislature.getUpdatedAt();
    }

    public Legislature(Long id, String description, Date dateStart, Date dateEnd, Timestamp createdAt,
        Timestamp updatedAt) {
            this.id = id;
            this.description = description;
            this.dateStart = dateStart;
            this.dateEnd = dateEnd;
            this.createdAt = createdAt;
            this.updatedAt = updatedAt;
    }

    public LegislatureDTO toLegislature() {
        return new LegislatureDTO(id, description, dateStart, dateEnd, createdAt, updatedAt);
    }

    public LegisLatureSimpleDTO toLegislatureSimple() {
        return new LegisLatureSimpleDTO(id, description, dateStart, dateEnd);
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public Date getDateStart() { return dateStart; }

    public void setDateStart(Date dateStart) { this.dateStart = dateStart; }

    public Date getDateEnd() { return dateEnd; }

    public void setDateEnd(Date dateEnd) { this.dateEnd = dateEnd; }

    public Timestamp getCreatedAt() { return createdAt; }

    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}
