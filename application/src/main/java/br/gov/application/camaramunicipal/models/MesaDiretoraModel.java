package br.gov.application.camaramunicipal.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "mesas_diretoras")
public class MesaDiretoraModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private Long id;
    
    @Getter
    @Setter
    @NotNull(message = "A legislatura é obrigatória.")
    @Column(name = "id_legislatura")
    private Long id_legislatura;

    @Getter
    @Setter
    @NotNull(message = "O cargo é obrigatório.")
    @Column(name = "id_cargo")
    private Long id_cargo;

    @Getter
    @Setter
    @NotNull(message = "O parlamentar é obrigatório.")
    @Column(name = "id_vereador")
    private Long id_vereador;

    @Getter
    @Setter
    @Column(name = "created_at")
    private Timestamp created_at;

    @Getter
    @Setter
    @Column(name = "updated_at")
    private Timestamp updated_at;
}