package com.desafio.codegroup.controllers;

import com.desafio.codegroup.entities.dtos.MembroProjetoDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface MembroProjetoResource {
    ResponseEntity<?> adicionarMembro(@PathVariable Long projetoId, @RequestBody MembroProjetoDTO membroDTO);
}

