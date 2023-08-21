package com.desafio.codegroup.services.impls;

import com.desafio.codegroup.entities.Pessoa;
import com.desafio.codegroup.repositories.PessoaRepository;
import com.desafio.codegroup.services.PessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PessoaServiceImpl implements PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    // O sistema não deve permitir o cadastro de um novo membro diretamente.
    // Deve ser provida funcionalidade via web service, contendo nome e atribuição (cargo).
    @Override
    public Pessoa save(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    @Override
    public Pessoa findById(Long pessoaId) {
        return pessoaRepository.findById(pessoaId).orElse(null);
    }

}
