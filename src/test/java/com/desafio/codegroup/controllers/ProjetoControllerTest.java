package com.desafio.codegroup.controllers;

import com.desafio.codegroup.controllers.impl.ProjetoController;
import com.desafio.codegroup.entities.Projeto;
import com.desafio.codegroup.entities.enums.Risco;
import com.desafio.codegroup.services.impls.ProjetoServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProjetoControllerTest {

    @Mock
    private ProjetoServiceImpl projetoService;

    @InjectMocks
    private ProjetoController projetoController;

    @Test
    public void testGetAllProjetosWithAvailableProjetos() {
        // Preparação
        List<Projeto> projetos = Arrays.asList(new Projeto(), new Projeto());
        when(projetoService.findAll()).thenReturn(projetos);

        // Ação
        ResponseEntity<?> response = projetoController.getAllProjetos();

        // Verificação adicional: imprime a exceção se o status for 500
        if (response.getStatusCode() == HttpStatus.INTERNAL_SERVER_ERROR) {
            System.out.println(response.getBody());
        }

        // Verificações
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(projetos, response.getBody());
    }

    @Test
    public void testGetAllProjetosWithNoAvailableProjetos() {
        // Preparação
        when(projetoService.findAll()).thenReturn(Collections.emptyList());

        // Ação
        ResponseEntity<?> response = projetoController.getAllProjetos();

        // Verificações
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNull(response.getBody());
    }

    @Test
    public void testGetProjetoByIdWhenProjetoIsFound() {
        // Preparação
        Long projetoId = 1L;
        Projeto projeto = new Projeto();
        projeto.setId(projetoId);
        when(projetoService.findById(projetoId)).thenReturn(projeto);

        // Ação
        ResponseEntity<?> response = projetoController.getProjetoById(projetoId);

        // Verificações
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(projeto, response.getBody());
    }

    @Test
    public void testGetProjetoByIdWhenProjetoIsNotFound() {
        // Preparação
        Long projetoId = 1L;
        when(projetoService.findById(projetoId)).thenReturn(null);

        // Ação
        ResponseEntity<?> response = projetoController.getProjetoById(projetoId);

        // Verificações
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertNull(response.getBody());
    }



    @Test
    public void testCreateProjetoWhenProjetoIsCreatedSuccessfully() {
        // Preparação
        Projeto projeto = new Projeto();
        Projeto createdProjeto = new Projeto();
        createdProjeto.setId(1L);
        when(projetoService.save(projeto)).thenReturn(createdProjeto);

        // Ação
        ResponseEntity<?> response = projetoController.createProjeto(projeto);

        // Verificações
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(createdProjeto, response.getBody());
    }

    @Test
    public void testUpdateProjetoWhenProjetoIsUpdatedSuccessfully() {
        // Preparação
        Long projetoId = 1L;
        Projeto projeto = new Projeto();
        Projeto updatedProjeto = new Projeto();
        updatedProjeto.setId(projetoId);

        when(projetoService.existsById(projetoId)).thenReturn(true);
        when(projetoService.save(projeto)).thenReturn(updatedProjeto);

        // Ação
        ResponseEntity<?> response = projetoController.updateProjeto(projetoId, projeto);

        // Verificações
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedProjeto, response.getBody());
    }

    @Test
    public void testDeleteProjetoWhenProjetoIsDeletedSuccessfully() {
        // Preparação
        Long projetoId = 1L;
        when(projetoService.existsById(projetoId)).thenReturn(true);

        // Ação
        ResponseEntity<?> response = projetoController.deleteProjeto(projetoId);

        // Verificações
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }
}
