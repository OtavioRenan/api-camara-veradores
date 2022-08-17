package br.gov.application.camaramunicipal.domain.dtos;

import java.sql.Date;
import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.gov.application.camaramunicipal.domain.Parliamentary;
import br.gov.application.camaramunicipal.utils.FactoryFormatDateUtil;

public class ParliamentaryDTO {
    private Long id;

    @NotNull(message = "O partido é obrigatório.")
    @NotEmpty(message = "O partido é obrigatório.")
    private Long politicalParyId;

    @NotNull(message = "A legislatura é obrigatória.")
    @NotEmpty(message = "A legislatura é obrigatória.")
    private Long legislatureId;
    
    @NotNull(message = "O nome é obrigatório.")
    @NotEmpty(message = "O nome é obrigatório.")
    private String name;

    @NotNull(message = "O nome social é obrigatório.")
    @NotEmpty(message = "O nome social é obrigatório.")
    private String socialName;

    @NotNull(message = "O e-mail é obrigatório.")
    @NotEmpty(message = "O e-mail é obrigatório.")
    private String email;

    @NotNull(message = "O telefone é obrigatório.")
    @NotEmpty(message = "O telefone é obrigatório.")
    private String numberPhone;

    @NotNull(message = "A data de nascimento é obrigatória.")
    @NotEmpty(message = "A data de nascimento é obrigatória.")
    private Date birth;

    private Timestamp createdAt;

    private Timestamp updatedAt;

    private String birthBr;

    private String createdAtBr;

    private String updatedAtBr;

    private final FactoryFormatDateUtil dateUtil = new FactoryFormatDateUtil();

    private String formatDate(Date date) { return dateUtil.formatDateBr(date); }

    private String formatDate(Timestamp date) { return dateUtil.formatDateBr(date); }

    public ParliamentaryDTO() {}

    public ParliamentaryDTO(Parliamentary parliamentary) {
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

        birthBr = formatDate( parliamentary.getBirth() );
        createdAtBr = formatDate( parliamentary.getCreatedAt() );
        updatedAtBr = formatDate( parliamentary.getUpdatedAt() );
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

    public String getBirthBr() { return birthBr; }

    public void setBirthBr(String birthBr) { this.birthBr = birthBr; }

    public String getCreatedAtBr() { return createdAtBr; }

    public void setCreatedAtBr(String createdAtBr) { this.createdAtBr = createdAtBr; }

    public String getUpdatedAtBr() { return updatedAtBr; }

    public void setUpdatedAtBr(String updatedAtBr) { this.updatedAtBr = updatedAtBr; }
}
