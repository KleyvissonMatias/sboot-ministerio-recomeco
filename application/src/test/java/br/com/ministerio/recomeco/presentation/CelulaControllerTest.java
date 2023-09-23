package br.com.ministerio.recomeco.presentation;

import br.com.ministerio.recomeco.domain.dto.Celula;
import br.com.ministerio.recomeco.exception.MinisterioRecomecoException;
import br.com.ministerio.recomeco.service.CelulaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static br.com.ministerio.recomeco.domain.mock.CelulaMock.criarCelulaMock;
import static br.com.ministerio.recomeco.domain.mock.CelulaMock.criarCelulasMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class CelulaControllerTest {
    @Mock
    private CelulaService celulaService;

    @InjectMocks
    private CelulaController celulaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInserir_Celula() throws Exception {
        Celula celula = criarCelulaMock();

        doNothing().when(celulaService).inserir(celula);

        ResponseEntity<?> response = celulaController.inserirCelula(celula);

        verify(celulaService, times(1)).inserir(celula);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testInserirCelula_Exception() throws Exception {
        Celula celula = criarCelulaMock();

        doThrow(new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, "Teste")).when(celulaService).inserir(celula);

        ResponseEntity<?> response = celulaController.inserirCelula(celula);

        verify(celulaService, times(1)).inserir(celula);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testAtualizarCelula_Success() throws Exception {
        Celula celula = criarCelulaMock();
        Celula celulaAtualizada = criarCelulaMock();

        when(celulaService.atualizar(celula)).thenReturn(celulaAtualizada);

        ResponseEntity<?> response = celulaController.atualizarCelula(celula);

        verify(celulaService, times(1)).atualizar(celula);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(celulaAtualizada, response.getBody());
    }

    @Test
    public void testAtualizarCelula_Exception() throws Exception {
        Celula celula = criarCelulaMock();

        doThrow(new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, "Teste")).when(celulaService).atualizar(celula);

        ResponseEntity<?> response = celulaController.atualizarCelula(celula);

        verify(celulaService, times(1)).atualizar(celula);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testObterCelulaPorId_Success() throws Exception {
        Integer id = 1;
        Celula celula = criarCelulaMock();

        when(celulaService.obterPorId(id)).thenReturn(celula);

        ResponseEntity<?> response = celulaController.obterCelulaPorId(id);

        verify(celulaService, times(1)).obterPorId(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(celula, response.getBody());
    }

    @Test
    public void testObterCelulaPorId_NotFoundException() throws Exception {
        Integer id = 1;

        doThrow(new MinisterioRecomecoException(HttpStatus.NOT_FOUND, "Teste exception")).when(celulaService).obterPorId(id);

        ResponseEntity<?> response = celulaController.obterCelulaPorId(id);

        verify(celulaService, times(1)).obterPorId(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    //@Test
    public void testObterCelulaPorId_Exception() throws Exception {
        Integer id = 1;

        when(celulaService.obterPorId(id)).thenThrow(new Exception());
        assertThrows(Exception.class, () -> celulaController.obterCelulaPorId(id));
    }

    @Test
    public void testListarCelulas_Success() throws Exception {
        List<Celula> celulas = criarCelulasMock(1);

        when(celulaService.listar()).thenReturn(celulas);

        ResponseEntity<?> response = celulaController.listarCelulas();

        verify(celulaService, times(1)).listar();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(celulas, response.getBody());
    }

    @Test
    public void testListarCelulas_Exception() throws Exception {
        doThrow(new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, "Teste exception")).when(celulaService).listar();

        ResponseEntity<?> response = celulaController.listarCelulas();

        verify(celulaService, times(1)).listar();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testListarCelulasPorNome_Success() throws Exception {
        String nome = "Minha Célula";
        List<Celula> celulas = criarCelulasMock(1);

        when(celulaService.listarPorNome(nome)).thenReturn(celulas);

        ResponseEntity<?> response = celulaController.listarCelulasPorNome(nome);

        verify(celulaService, times(1)).listarPorNome(nome);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(celulas, response.getBody());
    }

    @Test
    public void testListarCelulasPorNome_Exception() throws Exception {
        String nome = "TestName";

        doThrow(new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, "Teste exception")).when(celulaService).listarPorNome(nome);

        ResponseEntity<?> response = celulaController.listarCelulasPorNome(nome);

        verify(celulaService, times(1)).listarPorNome(nome);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testListarCelulasPorLider_Success() throws Exception {
        String lider = "João Líder";
        List<Celula> celulas = criarCelulasMock(1);

        when(celulaService.listarPorLider(lider)).thenReturn(celulas);

        ResponseEntity<?> response = celulaController.listarCelulasPorLider(lider);

        verify(celulaService, times(1)).listarPorLider(lider);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(celulas, response.getBody());
    }

    @Test
    public void testListarCelulasPorLider_Exception() throws Exception {
        String lider = "TestLider";

        doThrow(new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, "Teste exception")).when(celulaService).listarPorLider(lider);

        ResponseEntity<?> response = celulaController.listarCelulasPorLider(lider);

        verify(celulaService, times(1)).listarPorLider(lider);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testDeletarCelula_Success() throws Exception {
        Integer id = 1;

        doNothing().when(celulaService).deletar(id);

        ResponseEntity<?> response = celulaController.deletarCelula(id);

        verify(celulaService, times(1)).deletar(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeletarCelula_Exception() throws Exception {
        Integer id = 1;

        doThrow(new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, "Teste exception")).when(celulaService).deletar(id);

        ResponseEntity<?> response = celulaController.deletarCelula(id);

        verify(celulaService, times(1)).deletar(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }
}
