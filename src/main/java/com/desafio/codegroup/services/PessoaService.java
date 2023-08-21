package com.desafio.codegroup.services;

import com.desafio.codegroup.entities.Pessoa;

public interface PessoaService {
    public Pessoa save(Pessoa pessoa);

    Pessoa findById(Long pessoaId);
}
