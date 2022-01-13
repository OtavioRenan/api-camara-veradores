package br.gov.application.camaramunicipal.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "mesas_diretoras")
public class MesaDiretoraModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull(message = "A legislatura é obrigatória.")
    @Column(name = "id_legislatura")
    private Long idLegislatura;

    @NotNull(message = "O cargo é obrigatório.")
    @Column(name = "id_cargo")
    private Long idCargo;

    @NotNull(message = "O parlamentar é obrigatório.")
    @Column(name = "id_vereador")
    private Long idVereador;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}