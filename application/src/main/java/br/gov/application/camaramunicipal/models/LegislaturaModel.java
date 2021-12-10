package br.gov.application.camaramunicipal.models;

import java.sql.Date;
import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "legislaturas")
public class LegislaturaModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private Long id;
    
    @Getter
    @Setter
    @NotEmpty(message = "A descrição é obrigatória.")
    @Column(name = "descricao")
    private String descricao;

    @Getter
    @Setter
    @NotEmpty(message = "A data de início é obrigatória.")
    @Column(name = "data_inicio")
    private Date data_inicio;

    @Getter
    @Setter
    @NotEmpty(message = "A data do termino é obrigatória.")
    @Column(name = "data_fim")
    private Date data_fim;

    @Getter
    @Setter
    @Column(name = "created_at")
    private Timestamp created_at;

    @Getter
    @Setter
    @Column(name = "updated_at")
    private Timestamp updated_at;
}