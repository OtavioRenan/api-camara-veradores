package br.gov.application.camaramunicipal.domain.dtos.simples;

import java.sql.Date;

import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;

public class LegisLatureSimpleDTO {
    private Long id;
    
    private String description;

    private String dateStartBr;

    private String dateEndBr;

    private String formatDate(Date date) {
        return new FactoryFormatDateUtil().formatDateBr(date);
    }

    public LegisLatureSimpleDTO(Long id, String description, Date dateStart, Date dateEnd) {
        this.id = id;
        this.description = description;

        this.dateStartBr = formatDate( dateStart );
        this.dateEndBr = formatDate( dateEnd );
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public String getDateStartBr() { return dateStartBr; }

    public void setDateStartBr(String dateStart) { this.dateStartBr = dateStart; }

    public String getDateEndBr() { return dateEndBr; }

    public void setDateEndBr(String dateEnd) { this.dateEndBr = dateEnd; }
}