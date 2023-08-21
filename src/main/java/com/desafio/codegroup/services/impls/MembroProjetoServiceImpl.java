package com.desafio.codegroup.services.impls;

import com.desafio.codegroup.entities.MembroProjeto;
import com.desafio.codegroup.repositories.MembroProjetoRepository;
import com.desafio.codegroup.services.MembroProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MembroProjetoServiceImpl implements MembroProjetoService {
    @Autowired
    private MembroProjetoRepository membroProjetoRepository;

    // O sistema deve permitir associar membros aos projetos que tem atribuição funcionário
    @Override
    public MembroProjeto save(MembroProjeto membroProjeto) {
        return membroProjetoRepository.save(membroProjeto);
    }

}

