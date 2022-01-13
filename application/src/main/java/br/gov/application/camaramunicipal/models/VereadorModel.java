package br.gov.application.camaramunicipal.models;

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

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "vereadores")
public class VereadorModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "O partido é obrigatório.")
    @Column(name = "id_partido")
    private Long idPartido;

    @NotNull(message = "A legislatura é obrigatória.")
    @Column(name = "id_legislatura")
    private Long idLegislatura;
    
    @NotEmpty(message = "O nome é obrigatório.")
    private String nome;

    @NotEmpty(message = "O nome social é obrigatório.")
    @Column(name = "nomeSocial")
    private String nomeSocial;

    @Email(message = "Informe um e-mail válido.")
    private String email;

    private String telefone;

    @NotEmpty(message = "O nascimento é obrigatório.")
    private Date nascimento;

    @Column(name = "created_at")
    private Timestamp createdAt;

    @Column(name = "updated_at")
    private Timestamp updatedAt;
}