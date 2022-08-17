package br.gov.application.camaramunicipal.domain;

import java.sql.Date;
import java.sql.Timestamp;

import br.gov.application.camaramunicipal.domain.dtos.ParliamentaryDTO;
import br.gov.application.camaramunicipal.domain.dtos.simples.ParliamentarySimpleDTO;
import br.gov.application.camaramunicipal.infra.adapters.entitys.ParliamentaryEntity;

public class Parliamentary {
    private Long id;

    private Long politicalParyId;

    private Long legislatureId;
    
    private String name;

    private String socialName;

    private String email;

    private String numberPhone;

    private Date birth;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    public Parliamentary() {}

    public Parliamentary(ParliamentaryEntity parliamentary) {
        id = parliamentary.getId();
        politicalParyId = parliamentary.getPoliticalParyId();
        legislatureId = parliamentary.getLegislatureId();
        name = parliamentary.getName();
        socialName = parliamentary.getSocialName();
        email = parliamentary.getEmail();
        numberPhone = parliamentary.getNumberPhone();
        birth = parliamentary.getBirth();
        createdAt = parliamentary.getCreatedAt();
        updatedAt = parliamentary.getUpdatedAt();
    }

    public Parliamentary(ParliamentaryDTO parliamentary) {
        id = parliamentary.getId();
        politicalParyId = parliamentary.getPoliticalParyId();
        legislatureId = parliamentary.getLegislatureId();
        name = parliamentary.getName();
        socialName = parliamentary.getSocialName();
        email = parliamentary.getEmail();
        numberPhone = parliamentary.getNumberPhone();
        birth = parliamentary.getBirth();
        createdAt = parliamentary.getCreatedAt();
        updatedAt = parliamentary.getUpdatedAt();
    }

    public ParliamentaryDTO toParliamentaryDTO() {
        return new ParliamentaryDTO(this);
    }

    public ParliamentarySimpleDTO toParliamentarySimpleDTO() {
        return new ParliamentarySimpleDTO(this); 
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getPoliticalParyId() { return politicalParyId; }

    public void setPoliticalParyId(Long politicalParyId) { this.politicalParyId = politicalParyId; }

    public Long getLegislatureId() { return legislatureId; }

    public void setLegislatureId(Long legislatureId) { this.legislatureId = legislatureId; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getSocialName() { return socialName; }

    public void setSocialName(String socialName) { this.socialName = socialName; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getNumberPhone() { return numberPhone; }

    public void setNumberPhone(String numberPhone) { this.numberPhone = numberPhone; }

    public Date getBirth() { return birth; }

    public void setBirth(Date birth) { this.birth = birth; }

    public Timestamp getCreatedAt() { return createdAt; }

    public void setCreatedAt(Timestamp createdAt) { this.createdAt = createdAt; }

    public Timestamp getUpdatedAt() { return updatedAt; }

    public void setUpdatedAt(Timestamp updatedAt) { this.updatedAt = updatedAt; }
}