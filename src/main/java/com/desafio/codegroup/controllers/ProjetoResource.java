package com.desafio.codegroup.controllers;

import com.desafio.codegroup.entities.Projeto;
import com.desafio.codegroup.entities.enums.Risco;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public interface ProjetoResource {

    ResponseEntity<?> getAllProjetos();

    ResponseEntity<?> getProjetoById(@PathVariable Long id);

    ResponseEntity<?> createProjeto(@RequestBody Projeto projeto);

    ResponseEntity<?> updateProjeto(@PathVariable Long id, @RequestBody Projeto projeto);

    ResponseEntity<?> deleteProjeto(@PathVariable Long id);

    ResponseEntity<Risco> getRiscoDoProjeto(@PathVariable Long id);

    ResponseEntity<String> handleIllegalStateException(IllegalStateException e);

}

