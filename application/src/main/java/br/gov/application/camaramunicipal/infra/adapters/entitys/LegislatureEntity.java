package br.gov.application.camaramunicipal.infra.adapters.entitys;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.GenerationType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;

import br.gov.application.camaramunicipal.domain.Legislature;

@Entity(name = "legislatures")
public class LegislatureEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "A descrição é obrigatória.")
    private String description;

    @NotEmpty(message = "A data de início é obrigatória.")
    @Column(name = "date_start")
    private Date dateStart;

    @NotEmpty(message = "A data do termino é obrigatória.")
    @Column(name = "date_end")
    private Date dateEnd;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public LegislatureEntity() {}

    public LegislatureEntity(Legislature legislature) {
        id = legislature.getId();
        description = legislature.getDescription();
        dateStart = legislature.getDateStart();
        dateEnd = legislature.getDateEnd();
        createdAt = legislature.getCreatedAt();
        updatedAt = legislature.getUpdatedAt();
    }

    public Legislature toLegislature() {
        return new Legislature(this);
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