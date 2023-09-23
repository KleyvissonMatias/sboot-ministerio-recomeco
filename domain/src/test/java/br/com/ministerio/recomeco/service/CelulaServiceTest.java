package br.com.ministerio.recomeco.service;

import br.com.ministerio.recomeco.domain.dto.Celula;
import br.com.ministerio.recomeco.exception.MinisterioRecomecoException;
import br.com.ministerio.recomeco.port.CelulaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static br.com.ministerio.recomeco.domain.mock.CelulaMock.criarCelulaMock;
import static br.com.ministerio.recomeco.domain.mock.CelulaMock.criarCelulasMock;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class CelulaServiceTest {
    @Mock
    private CelulaRepository repository;

    @InjectMocks
    private CelulaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void inserirTesteRetornoComSucesso() {
        Celula celulaMock = mock(Celula.class);
        this.service.inserir(celulaMock);

        verify(this.repository, times(1)).criar(celulaMock);
    }

    @Test
    public void atualizarTesteRetornoComSucesso() {
        Celula celulaRetorno = criarCelulaMock();
        when(this.repository.obterPorId(1)).thenReturn(celulaRetorno);
        Celula celula = this.service.atualizar(celulaRetorno);

        assertNotNull(celula);
    }

    @Test
    public void deletarTesteRetornoComSucesso() {
        Celula celulaMock = criarCelulaMock();
        when(this.repository.obterPorId(criarCelulaMock().getId())).thenReturn(celulaMock);
        when(this.service.obterPorId(criarCelulaMock().getId())).thenReturn(celulaMock);
        this.service.deletar(celulaMock.getId());

        verify(this.repository, times(1)).deletar(celulaMock.getId());
    }

    @Test
    public void deletarTesteRetornoExceptionNotFound() {
        assertThrows(MinisterioRecomecoException.class, () -> {
            Celula celulaMock = criarCelulaMock();
            this.service.deletar(celulaMock.getId());
        });
    }

    @Test
    public void listarTesteRetornoSucesso() {
        List<Celula> celulas = criarCelulasMock(1);
        when(this.repository.listar()).thenReturn(celulas);
        var retorno = this.service.listar();

        assertNotNull(retorno);
    }

    @Test
    public void listarTesteRetornoExceptionNotFound() {
        assertThrows(MinisterioRecomecoException.class, () -> {
            var retorno = this.service.listar();
        });
    }

    @Test
    public void testListarPorNomeSucesso() {
        List<Celula> celulas = criarCelulasMock(1);
        when(repository.listarPorNome(anyString())).thenReturn(celulas);
        List<Celula> result = this.service.listarPorNome("Minha Célula");

        assertEquals(celulas, result);
    }

    @Test
    public void testListarPorNomeSemRegistros() {
        when(repository.listarPorNome(anyString())).thenReturn(new ArrayList<>());
        MinisterioRecomecoException exception = assertThrows(MinisterioRecomecoException.class, () -> {
            this.service.listarPorNome("Célula");
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void testListarPorLiderSucesso() {
        List<Celula> celulas = criarCelulasMock(1);
        when(repository.listarPorLider(anyString())).thenReturn(celulas);
        List<Celula> result = this.service.listarPorLider("João Líder");

        assertEquals(celulas, result);
    }

    @Test
    public void testListarPorLiderSemRegistros() {
        when(repository.listarPorLider(anyString())).thenReturn(new ArrayList<>());
        MinisterioRecomecoException exception = assertThrows(MinisterioRecomecoException.class, () -> {
            this.service.listarPorLider("Lider");
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }
}
