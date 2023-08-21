package com.desafio.codegroup.controllers.impl;

import com.desafio.codegroup.controllers.PessoaResource;
import com.desafio.codegroup.entities.Pessoa;
import com.desafio.codegroup.services.PessoaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Pessoa", description = "Gerenciamento de Membros")
@RestController
public class PessoaController implements PessoaResource {

    private final PessoaService pessoaService;

    public PessoaController(PessoaService pessoaService) {
        this.pessoaService = pessoaService;
    }

    @Operation(
            summary = "Cria um novo membro",
            description = "Permite o cadastro de um novo membro com nome e atribuição (cargo). Não permite o cadastro direto via interface."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Membro criado com sucesso.",
                    content = { @Content(schema = @Schema(implementation = Pessoa.class), mediaType = "application/json") }
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro de validação ou dados inválidos.",
                    content = { @Content(schema = @Schema(implementation = String.class)) }
            ),
            @ApiResponse(
                    responseCode = "500",
                    description = "Erro interno do servidor.",
                    content = { @Content() }
            )
    })
    @PostMapping("/membros")
    public ResponseEntity<?> createMembro(@RequestBody Pessoa pessoa) {
        if (pessoa == null || pessoa.getNome() == null || pessoa.getAtribuicao() == null) {
            return ResponseEntity.badRequest().body("Dados inválidos fornecidos.");
        }

        try {
            Pessoa savedPessoa = pessoaService.save(pessoa);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedPessoa);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Ocorreu um erro ao criar o membro.");
        }
    }
}
