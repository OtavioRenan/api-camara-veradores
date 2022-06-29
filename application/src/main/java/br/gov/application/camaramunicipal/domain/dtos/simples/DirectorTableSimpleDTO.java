package br.gov.application.camaramunicipal.domain.dtos.simples;

public class DirectorTableSimpleDTO {
    private Long id;
    
    private Long legislatureId;

    private Long adjutancyId;

    private Long parliamentaryId;

    public DirectorTableSimpleDTO() {}

    public DirectorTableSimpleDTO(Long id, Long legislatureId, Long adjutancyId, Long parliamentaryId) {
        this.id = id;
        this.legislatureId = legislatureId;
        this.adjutancyId = adjutancyId;
        this.parliamentaryId = parliamentaryId;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public Long getLegislatureId() { return legislatureId; }

    public void setLegislatureId(Long legislatureId) { this.legislatureId = legislatureId; }

    public Long getAdjutancyId() { return adjutancyId; }

    public void setAdjutancyId(Long adjutancyId) { this.adjutancyId = adjutancyId; }

    public Long getParliamentaryId() { return parliamentaryId; }

    public void setParliamentaryId(Long parliamentaryId) { this.parliamentaryId = parliamentaryId; }
}