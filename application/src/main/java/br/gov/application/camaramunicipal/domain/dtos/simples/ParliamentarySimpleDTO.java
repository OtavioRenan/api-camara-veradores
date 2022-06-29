package br.gov.application.camaramunicipal.domain.dtos.simples;

import java.sql.Date;

import br.gov.application.camaramunicipal.domain.Parliamentary;
import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;

public class ParliamentarySimpleDTO {
    private Long id;

    private String socialName;

    private String email;

    private String telefone;

    private String birthBr;

    private final FactoryFormatDateUtil dateUtil = new FactoryFormatDateUtil();

    private String formatDate(Date date) {
        return dateUtil.formatDateBr(date);
    }

    public ParliamentarySimpleDTO() {}

    public ParliamentarySimpleDTO(Parliamentary parliamentary) {
        id = parliamentary.getId();
        socialName = parliamentary.getSocialName();
        email = parliamentary.getEmail();
        telefone = parliamentary.getTelefone();

        birthBr = formatDate( parliamentary.getBirth() );
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getSocialName() { return socialName; }

    public void setSocialName(String socialName) { this.socialName = socialName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getTelefone() { return telefone; }

    public void setTelefone(String telefone) { this.telefone = telefone; }

    public String getBirthBr() { return birthBr; }

    public void setBirthBr(String birthBr) { this.birthBr = birthBr; }
}