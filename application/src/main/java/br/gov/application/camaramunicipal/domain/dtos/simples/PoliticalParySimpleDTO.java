package br.gov.application.camaramunicipal.domain.dtos.simples;

public class PoliticalParySimpleDTO {
    private Long id;
    
    private String name;

    private String initials;

    public PoliticalParySimpleDTO() {}

    public PoliticalParySimpleDTO(Long id, String name, String initials) {
        this.id = id;
        this.name = name;
        this.initials = initials;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getInitials() { return initials; }
}