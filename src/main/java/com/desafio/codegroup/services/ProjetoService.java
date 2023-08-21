package com.desafio.codegroup.services;

import com.desafio.codegroup.entities.Projeto;
import com.desafio.codegroup.entities.enums.Risco;
import com.desafio.codegroup.entities.enums.StatusProjeto;

import java.util.List;

public interface ProjetoService {

    public List<Projeto> findAll();

    public Projeto findById(Long id);

    public Projeto save(Projeto projeto);

    public Risco determinarRisco(Projeto projeto);

    public Projeto mudarStatus(Long id, StatusProjeto novoStatus);

    public void delete(Long id);

    public boolean existsById(Long id);


}

