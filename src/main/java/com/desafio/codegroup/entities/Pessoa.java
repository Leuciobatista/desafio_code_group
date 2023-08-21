package com.desafio.codegroup.entities;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "PESSOAS")
public class Pessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 100)
    private String nome;

    @Column(name = "datanascimento")
    private Date dataNascimento;

    @Column(name = "cpf", length = 14)
    private String cpf;

    @Column(name = "funcionario")
    private Boolean funcionario;

    @Column(name = "atribuicao", length = 100)
    private String atribuicao;

}
