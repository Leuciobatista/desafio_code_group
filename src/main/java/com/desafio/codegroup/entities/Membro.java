package com.desafio.codegroup.entities;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "MEMBROS")
@IdClass(MembrosId.class)
public class Membro {
    @Id
    @Column(name = "idprojeto")
    private Long idProjeto;

    @Id
    @Column(name = "idpessoa")
    private Long idPessoa;

    @ManyToOne
    @JoinColumn(name = "idprojeto", referencedColumnName = "id", insertable = false, updatable = false)
    private Projeto projeto;

    @ManyToOne
    @JoinColumn(name = "idpessoa", referencedColumnName = "id", insertable = false, updatable = false)
    private Pessoa pessoa;

}
