package br.gov.application.camaramunicipal.infra.adapters.entitys;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import br.gov.application.camaramunicipal.domain.Parliamentary;

@Entity(name = "parliamentarys")
public class ParliamentaryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O partido é obrigatório.")
    @Column(name = "political_pary_id")
    private Long politicalParyId;

    @NotNull(message = "A legislatura é obrigatória.")
    @Column(name = "legislature_id")
    private Long legislatureId;
    
    @NotEmpty(message = "O nome é obrigatório.")
    private String name;

    @NotEmpty(message = "O nome social é obrigatório.")
    @Column(name = "social_name")
    private String socialName;

    @Email(message = "Informe um e-mail válido.")
    private String email;

    @Column(name = "number_phone")
    private String numberPhone;

    @NotEmpty(message = "O nascimento é obrigatório.")
    private Date birth;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;

    public ParliamentaryEntity() {}

    public ParliamentaryEntity(Parliamentary parliamentary) {
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

    public Parliamentary toParliamentary() {
        return new Parliamentary(this);
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