package com.desafio.codegroup.controllers;

import com.desafio.codegroup.controllers.impl.PessoaController;
import com.desafio.codegroup.entities.Pessoa;
import com.desafio.codegroup.services.PessoaService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PessoaControllerTest {

    @Mock
    private PessoaService pessoaService;

    @InjectMocks
    private PessoaController pessoaController;

    @Test
    public void testCreateMembroWithValidData() {
        // Preparação
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setAtribuicao("Developer");

        when(pessoaService.save(pessoa)).thenReturn(pessoa);

        // Ação
        ResponseEntity<?> response = pessoaController.createMembro(pessoa);

        // Verificações
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals(pessoa, response.getBody());
    }

    @Test
    public void testCreateMembroWithNullData() {
        // Ação
        ResponseEntity<?> response = pessoaController.createMembro(null);

        // Verificações
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Dados inválidos fornecidos.", response.getBody());
    }

    @Test
    public void testCreateMembroWithNullName() {
        // Preparação
        Pessoa pessoa = new Pessoa();
        pessoa.setNome(null);
        pessoa.setAtribuicao("Developer");

        // Ação
        ResponseEntity<?> response = pessoaController.createMembro(pessoa);

        // Verificações
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Dados inválidos fornecidos.", response.getBody());
    }

    @Test
    public void testCreateMembroWithNullAtribuicao() {
        // Preparação
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setAtribuicao(null);

        // Ação
        ResponseEntity<?> response = pessoaController.createMembro(pessoa);

        // Verificações
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Dados inválidos fornecidos.", response.getBody());
    }

    @Test
    public void testCreateMembroWithServiceException() {
        // Preparação
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setAtribuicao("Developer");

        // Simula uma exceção ao tentar salvar a Pessoa
        when(pessoaService.save(pessoa)).thenThrow(new RuntimeException("Erro simulado ao salvar"));

        // Ação
        ResponseEntity<?> response = pessoaController.createMembro(pessoa);

        // Verificações
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Ocorreu um erro ao criar o membro.", response.getBody());
    }

    @Test
    public void testCreateMembroServiceCall() {
        // Preparação
        Pessoa pessoa = new Pessoa();
        pessoa.setNome("João");
        pessoa.setAtribuicao("Developer");

        when(pessoaService.save(pessoa)).thenReturn(pessoa);

        // Ação
        pessoaController.createMembro(pessoa);

        // Verificações
        verify(pessoaService).save(pessoa);
    }
}
