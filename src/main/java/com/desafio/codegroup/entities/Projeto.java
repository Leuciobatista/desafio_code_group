package com.desafio.codegroup.entities;

import com.desafio.codegroup.entities.enums.StatusProjeto;
import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Data
@Entity
@Table(name = "PROJETOS")
public class Projeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "data_inicio")
    private Date dataInicio;

    @Column(name = "data_previsao_fim")
    private Date dataPrevisaoFim;

    @Column(name = "data_fim")
    private Date dataFim;

    @Column(name = "descricao", length = 5000)
    private String descricao;

    @Column(name = "orcamento")
    private Float orcamento;

    @Column(name = "risco", length = 45)
    private String risco;

    @ManyToOne
    @JoinColumn(name = "idgerente", nullable = false)
    private Pessoa gerente;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", length = 45)
    private StatusProjeto status;

}
