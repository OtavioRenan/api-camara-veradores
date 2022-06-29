package br.gov.application.camaramunicipal.domain.dtos;

import java.sql.Date;
import java.sql.Timestamp;

import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;

public class LegislatureDTO {
    private Long id;
    
    private String description;

    private Date dateStart;

    private Date dateEnd;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String dateStartBr;

    private String dateEndBr;

    private String createdAtBr;

    private String updatedAtBr;

    private final FactoryFormatDateUtil dateUtil = new FactoryFormatDateUtil();

    private String formatDate(Date date) {
        return dateUtil.formatDateBr(date);
    }

    private String formatDate(Timestamp date) {
        return dateUtil.formatDateBr(date);
    }

    public LegislatureDTO() {}

    public LegislatureDTO(Long id, String description, Date dateStart, Date dateEnd, Timestamp createdAt, Timestamp updatedAt) {
        this.id = id;
        this.description = description;
        this.dateStart = dateStart;
        this.dateEnd = dateEnd;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;

        this.dateStartBr = formatDate( dateStart );
        this.dateEndBr = formatDate( dateEnd );
        this.createdAtBr = formatDate( createdAt );
        this.updatedAtBr = formatDate( updatedAt );
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

    public String getDateStartBr() { return dateStartBr; }

    public void setDateStartBr(String dateStartBr) { this.dateStartBr = dateStartBr; }

    public String getDateEndBr() { return dateEndBr; }

    public void setDateEndBr(String dateEndBr) { this.dateEndBr = dateEndBr; }

    public String getCreatedAtBr() { return createdAtBr; }

    public void setCreatedAtBr(String createdAtBr) { this.createdAtBr = createdAtBr; }

    public String getUpdatedAtBr() { return updatedAtBr; }

    public void setUpdatedAtBr(String updatedAtBr) { this.updatedAtBr = updatedAtBr; }
}