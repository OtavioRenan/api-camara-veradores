package br.gov.application.camaramunicipal.models;

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
@Entity(name = "partidos")
public class PartidoModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "O nome é obrigatório.")
    private String nome;

    @NotEmpty(message = "A sigla é obrigatório.")
    private String sigla;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Getter
    @Setter
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}