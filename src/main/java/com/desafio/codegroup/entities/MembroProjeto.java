package com.desafio.codegroup.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "membros_projeto")
public class MembroProjeto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "idprojeto", nullable = false)
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "idpessoa", nullable = false)
    private Pessoa pessoa;

    // Getters e setters
}

