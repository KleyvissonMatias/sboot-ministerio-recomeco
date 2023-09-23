package br.com.ministerio.recomeco.service;

import br.com.ministerio.recomeco.domain.dto.Vida;
import br.com.ministerio.recomeco.exception.MinisterioRecomecoException;
import br.com.ministerio.recomeco.port.VidaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;

import java.util.ArrayList;
import java.util.List;

import static br.com.ministerio.recomeco.domain.mock.VidaMock.criarVidasMock;
import static br.com.ministerio.recomeco.domain.mock.VidaMock.vidaMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

public class VidaServiceTest {
    @Mock
    private VidaRepository repository;

    @InjectMocks
    private VidaService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void inserirTesteRetornoComSucesso() {
        Vida vidaMock = Mockito.mock(Vida.class);
        this.service.inserir(vidaMock);

        verify(this.repository, times(1)).criar(vidaMock);
    }

    @Test
    public void atualizarTesteRetornoComSucesso() {
        Vida vidaRetorno = vidaMock();
        when(this.repository.obterPorId(1)).thenReturn(vidaRetorno);
        Vida vida = this.service.atualizar(vidaRetorno);

        Assertions.assertNotNull(vida);
    }

    @Test
    public void deletarTesteRetornoComSucesso() {
        Vida vidaMock = vidaMock();
        when(this.repository.obterPorId(vidaMock().getId())).thenReturn(vidaMock);
        when(this.service.obterPorId(vidaMock().getId())).thenReturn(vidaMock);
        this.service.deletar(vidaMock.getId());

        verify(this.repository, times(1)).deletar(vidaMock.getId());
    }

    @Test
    public void deletarTesteRetornoExceptionNotFound() {
        assertThrows(MinisterioRecomecoException.class, () -> {
            Vida vidaMock = vidaMock();
            this.service.deletar(vidaMock.getId());
        });
    }

    @Test
    public void listarTesteRetornoSucesso() {
        List<Vida> vidas = criarVidasMock(1);
        when(this.repository.listar()).thenReturn(vidas);
        var retorno = this.service.listar();

        Assertions.assertNotNull(retorno);
    }

    @Test
    public void listarTesteRetornoExceptionNotFound() {
        assertThrows(MinisterioRecomecoException.class, () -> {
            var retorno = this.service.listar();
        });
    }

    @Test
    public void testListarPorNomeSucesso() {
        List<Vida> vidas = criarVidasMock(1);
        when(repository.listarPorNome(anyString())).thenReturn(vidas);
        List<Vida> result = this.service.listarPorNome("Vida Nome Pessoa");

        assertEquals(vidas, result);
    }

    @Test
    public void testListarPorNomeSemRegistros() {
        when(repository.listarPorNome(anyString())).thenReturn(new ArrayList<>());
        MinisterioRecomecoException exception = assertThrows(MinisterioRecomecoException.class, () -> {
            this.service.listarPorNome("Nome");
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void testListarPorStatusSucesso() {
        List<Vida> vidas = criarVidasMock(1);
        when(repository.listarPorStatus(anyString())).thenReturn(vidas);
        List<Vida> result = this.service.listarPorStatus("Triagem");

        assertEquals(vidas, result);
    }

    @Test
    public void testListarPorStatusSemRegistros() {
        when(repository.listarPorStatus(anyString())).thenReturn(new ArrayList<>());
        MinisterioRecomecoException exception = assertThrows(MinisterioRecomecoException.class, () -> {
            this.service.listarPorStatus("Status");
        });
        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void testObterPorCpfSucesso() {
        String cpf = "12345678901";
        Vida vidaMock = vidaMock();
        when(repository.obterPorCpf(cpf)).thenReturn(vidaMock);

        Vida result = this.service.obterPorCpf(cpf);

        assertEquals(vidaMock, result);
    }

    @Test
    public void testObterPorCpfSemRegistros() {
        String cpf = "12345678901";
        when(repository.obterPorCpf(cpf)).thenReturn(null);

        MinisterioRecomecoException exception = assertThrows(MinisterioRecomecoException.class, () -> {
            this.service.obterPorCpf(cpf);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void testAtualizarStatusPorCpfSucesso() {
        String cpf = "12345678901";
        String novoStatus = "Ativo";
        Vida vidaMock = vidaMock();
        when(repository.obterPorCpf(cpf)).thenReturn(vidaMock);

        Vida result = this.service.atualizarStatusPorCpf(cpf, novoStatus);

        assertEquals(novoStatus, result.getStatus());
        verify(repository, times(1)).atualizar(vidaMock);
    }

    @Test
    public void testAtualizarStatusPorCpfSemRegistros() {
        String cpf = "12345678901";
        String novoStatus = "Ativo";
        when(repository.obterPorCpf(cpf)).thenReturn(null);

        MinisterioRecomecoException exception = assertThrows(MinisterioRecomecoException.class, () -> {
            this.service.atualizarStatusPorCpf(cpf, novoStatus);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }

    @Test
    public void testAtualizarStatusPorIdSucesso() {
        Integer id = 1;
        String novoStatus = "Ativo";
        Vida vidaMock = vidaMock();
        when(repository.obterPorId(id)).thenReturn(vidaMock);

        Vida result = this.service.atualizarStatusPorId(id, novoStatus);

        assertEquals(novoStatus, result.getStatus());
        verify(repository, times(1)).atualizar(vidaMock);
    }

    @Test
    public void testAtualizarStatusPorIdSemRegistros() {
        Integer id = 1;
        String novoStatus = "Ativo";
        when(repository.obterPorId(id)).thenReturn(null);

        MinisterioRecomecoException exception = assertThrows(MinisterioRecomecoException.class, () -> {
            this.service.atualizarStatusPorId(id, novoStatus);
        });

        assertEquals(HttpStatus.NOT_FOUND, exception.getStatus());
    }
}
