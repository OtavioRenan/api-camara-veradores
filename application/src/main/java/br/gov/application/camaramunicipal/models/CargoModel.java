package br.gov.application.camaramunicipal.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "cargos")
public class CargoModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotEmpty(message = "O nome é obrigatório.")
    private String nome;

    @NotEmpty(message = "A descrição é obrigatório.")
    private String descricao;

    @JsonIgnore
    @Column(name = "created_at")
    private Timestamp createdAt;
    
    @JsonIgnore
    @Column(name = "updated_at")
    private Timestamp updatedAt;
}