package com.desafio.codegroup.repositories;

import com.desafio.codegroup.entities.MembroProjeto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembroProjetoRepository extends JpaRepository<MembroProjeto, Long> {
}

