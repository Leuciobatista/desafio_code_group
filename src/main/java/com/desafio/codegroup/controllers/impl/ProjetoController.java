package com.desafio.codegroup.controllers.impl;

import com.desafio.codegroup.controllers.ProjetoResource;
import com.desafio.codegroup.entities.Projeto;
import com.desafio.codegroup.entities.enums.Risco;
import com.desafio.codegroup.services.impls.ProjetoServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "Projeto", description = "Gerenciamento de Projetos")
@RestController
@RequestMapping("/projetos")
public class ProjetoController implements ProjetoResource {

    private final ProjetoServiceImpl projetoService;

    private static final String ERROR_FETCHING_PROJECTS = "Ocorreu um erro ao buscar os projetos.";
    private static final String ERROR_CREATING_PROJECT = "Ocorreu um erro ao criar o projeto.";
    private static final String ERROR_UPDATING_PROJECT = "Ocorreu um erro ao atualizar o projeto.";

    @Autowired
    public ProjetoController(ProjetoServiceImpl projetoService) {
        this.projetoService = projetoService;
    }

    @Operation(summary = "Retorna todos os projetos")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Projeto.class), mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = Projeto.class))) }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }) })
    @GetMapping
    public ResponseEntity<List<Projeto>> getAllProjetos() {
        try {
            List<Projeto> projetos = projetoService.findAll();
            if (projetos.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(projetos);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Retorna um projeto específico")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Projeto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }) })
    @GetMapping("/{id}")
    public ResponseEntity<Projeto> getProjetoById(@PathVariable Long id) {
        try {
            Projeto projeto = projetoService.findById(id);
            if (projeto == null) {
                return ResponseEntity.notFound().build();
            }
            return ResponseEntity.ok(projeto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Cria um novo projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "201", content = { @Content(schema = @Schema(implementation = Projeto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }) })
    @PostMapping
    public ResponseEntity<Projeto> createProjeto(@RequestBody Projeto projeto) {
        try {
            Projeto createdProjeto = projetoService.save(projeto);
            return new ResponseEntity<>(createdProjeto, HttpStatus.CREATED);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Atualiza um projeto existente")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Projeto.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "400", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "500", content = { @Content(schema = @Schema(implementation = String.class), mediaType = "application/json") }) })
    @PutMapping("/{id}")
    public ResponseEntity<Projeto> updateProjeto(@PathVariable Long id, @RequestBody Projeto projeto) {
        try {
            if (!projetoService.existsById(id)) {
                return ResponseEntity.notFound().build();
            }
            projeto.setId(id);
            Projeto updatedProjeto = projetoService.save(projeto);
            return ResponseEntity.ok(updatedProjeto);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Exclui um projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Projeto excluído com sucesso"),
            @ApiResponse(responseCode = "404", description = "Projeto não encontrado para o ID fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProjeto(@PathVariable Long id) {
        if (!projetoService.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        try {
            projetoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @Operation(summary = "Obtem o risco de um projeto")
    @ApiResponses({
            @ApiResponse(responseCode = "200", content = { @Content(schema = @Schema(implementation = Risco.class), mediaType = "application/json") }),
            @ApiResponse(responseCode = "404", description = "Projeto não encontrado para o ID fornecido"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor") })
    @GetMapping("/{id}/risco")
    public ResponseEntity<Risco> getRiscoDoProjeto(@PathVariable Long id) {
        Projeto projeto = projetoService.findById(id);
        if (projeto == null) {
            return ResponseEntity.notFound().build();
        }

        try {
            // Supondo que o projeto.getRisco() retorne uma String, converta-a para o enum Risco
            Risco risco = Risco.valueOf(projeto.getRisco().toUpperCase());
            return ResponseEntity.ok(risco);
        } catch (IllegalArgumentException e) {
            // Se a conversão falhar, isso significa que a string não corresponde a nenhum valor Risco.
            // Você pode tratar isso como quiser, aqui estou retornando um erro 500 como exemplo.
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @ExceptionHandler(IllegalStateException.class)
    public ResponseEntity<String> handleIllegalStateException(IllegalStateException e) {
        return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
    }

}
