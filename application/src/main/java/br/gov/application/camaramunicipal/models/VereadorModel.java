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

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "vereadores")
public class VereadorModel
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @Column(name = "id")
    private Long id;

    @Getter
    @Setter
    @NotNull(message = "O partido é obrigatório.")
    @Column(name = "id_partido")
    private Long id_partido;

    @Getter
    @Setter
    @NotNull(message = "A legislatura é obrigatória.")
    @Column(name = "id_legislatura")
    private Long id_legislatura;
    
    @Getter
    @Setter
    @NotEmpty(message = "O nome é obrigatório.")
    @Column(name = "nome")
    private String nome;

    @Getter
    @Setter
    @NotEmpty(message = "O nome social é obrigatório.")
    @Column(name = "nome_social")
    private String nome_social;

    @Getter
    @Setter
    @Email(message = "Informe um e-mail válido.")
    @Column(name = "email")
    private String email;

    @Getter
    @Setter
    @Column(name = "telefone")
    private String telefone;

    @Getter
    @Setter
    @NotEmpty(message = "O nascimento é obrigatório.")
    @Column(name = "nascimento")
    private Date nascimento;

    @Getter
    @Setter
    @Column(name = "created_at")
    private Timestamp created_at;

    @Getter
    @Setter
    @Column(name = "updated_at")
    private Timestamp updated_at;
}