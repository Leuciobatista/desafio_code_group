package com.desafio.codegroup.controllers;

import com.desafio.codegroup.entities.Pessoa;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

public interface PessoaResource {

    ResponseEntity<?> createMembro(@RequestBody Pessoa pessoa);

}


