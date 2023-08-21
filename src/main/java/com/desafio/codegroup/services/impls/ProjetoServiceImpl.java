package com.desafio.codegroup.services.impls;

import com.desafio.codegroup.entities.Projeto;
import com.desafio.codegroup.entities.enums.Risco;
import com.desafio.codegroup.entities.enums.StatusProjeto;
import com.desafio.codegroup.repositories.ProjetoRepository;
import com.desafio.codegroup.services.ProjetoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjetoServiceImpl implements ProjetoService {
    @Autowired
    private ProjetoRepository projetoRepository;

    public List<Projeto> findAll() {
        return projetoRepository.findAll();
    }

    public Projeto findById(Long id) {
        return projetoRepository.findById(id).orElse(null);
    }

    public Projeto save(Projeto projeto) {
        return projetoRepository.save(projeto);
    }

    // Os projetos devem ser classificados em: baixo risco, médio risco e alto risco.
    // A classificação de risco não é cadastrada no sistema.
    public Risco determinarRisco(Projeto projeto) {
        if (projeto.getOrcamento() < 100000) {
            return Risco.BAIXO;
        } else if (projeto.getOrcamento() < 500000) {
            return Risco.MEDIO;
        } else {
            return Risco.ALTO;
        }
    }

    // A cada instante, o projeto deve estar em um status específico e único.
    // Os status possíveis não são cadastrados no sistema e são: em análise,
    // análise realizada, análise aprovada, iniciado, planejado, em andamento,
    // encerrado, cancelado.
    public Projeto mudarStatus(Long id, StatusProjeto novoStatus) {
        Projeto projeto = projetoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));

        // Exemplo de validação de transição de status
        if (novoStatus == StatusProjeto.INICIADO && projeto.getStatus() != StatusProjeto.ANALISE_APROVADA) {
            throw new IllegalStateException("O projeto deve estar em 'Análise Aprovada' para ser iniciado");
        }

        projeto.setStatus(novoStatus);
        return projetoRepository.save(projeto);
    }

    // Se um projeto foi mudado o status para iniciado, em andamento ou encerrado não pode mais ser excluído
    public void delete(Long id) {
        Projeto projeto = projetoRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Projeto não encontrado"));

        if (projeto.getStatus() == StatusProjeto.INICIADO
                || projeto.getStatus() == StatusProjeto.EM_ANDAMENTO
                || projeto.getStatus() == StatusProjeto.ENCERRADO) {
            throw new IllegalStateException("O projeto não pode ser excluído quando o status é Iniciado, Em Andamento ou Encerrado");
        }

        projetoRepository.deleteById(id);
    }

    public boolean existsById(Long id) {
        return projetoRepository.existsById(id);
    }


}

