package br.gov.application.camaramunicipal.models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotEmpty;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "legislaturas")
public class LegislaturaModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "A descrição é obrigatória.")
    private String descricao;

    @NotEmpty(message = "A data de início é obrigatória.")
    @Column(name = "data_inicio")
    private Date dataInicio;

    @NotEmpty(message = "A data do termino é obrigatória.")
    @Column(name = "data_fim")
    private Date dataFim;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}