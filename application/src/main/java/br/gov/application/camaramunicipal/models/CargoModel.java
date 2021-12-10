package br.gov.application.camaramunicipal.models;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import javax.validation.constraints.NotEmpty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "cargos")
public class CargoModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private Long id;
    
    @Getter
    @Setter
    @NotEmpty(message = "O nome é obrigatório.")
    @Column(name = "nome")
    private String nome;

    @Getter
    @Setter
    @NotEmpty(message = "A descrição é obrigatório.")
    @Column(name = "descricao")
    private String descricao;

    @Getter
    @Setter
    @Column(name = "created_at")
    private Timestamp created_at;

    @Getter
    @Setter
    @Column(name = "updated_at")
    private Timestamp updated_at;
}