package br.com.ministerio.recomeco.presentation;

import br.com.ministerio.recomeco.constant.UtilsConstants;
import br.com.ministerio.recomeco.domain.dto.Vida;
import br.com.ministerio.recomeco.exception.MinisterioRecomecoException;
import br.com.ministerio.recomeco.service.VidaService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;

import static br.com.ministerio.recomeco.domain.mock.VidaMock.criarVidasMock;
import static br.com.ministerio.recomeco.domain.mock.VidaMock.vidaMock;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class VidaControllerTest {

    @Mock
    private VidaService vidaService;

    @InjectMocks
    private VidaController vidaController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testInserir_Vida() throws Exception {
        Vida vida = vidaMock();

        doNothing().when(vidaService).inserir(vida);

        ResponseEntity<?> response = vidaController.inserirVida(vida);

        verify(vidaService, times(1)).inserir(vida);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }

    @Test
    public void testInserirVida_Exception() throws Exception {
        Vida vida = vidaMock();

        doThrow(new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, UtilsConstants.EXCEPTION)).when(vidaService).inserir(vida);

        ResponseEntity<?> response = vidaController.inserirVida(vida);

        verify(vidaService, times(1)).inserir(vida);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testAtualizarVida_Success() throws Exception {
        Vida vida = vidaMock();
        Vida vidaAtualizada = vidaMock();

        when(vidaService.atualizar(vida)).thenReturn(vidaAtualizada);

        ResponseEntity<?> response = vidaController.atualizarVida(vida);

        verify(vidaService, times(1)).atualizar(vida);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vidaAtualizada, response.getBody());
    }

    @Test
    public void testAtualizarVida_Exception() throws Exception {
        Vida vida = vidaMock();

        doThrow(new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, UtilsConstants.EXCEPTION)).when(vidaService).atualizar(vida);

        ResponseEntity<?> response = vidaController.atualizarVida(vida);

        verify(vidaService, times(1)).atualizar(vida);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testObterVidaPorId_Success() throws Exception {
        Integer id = 1;
        Vida vida = vidaMock();

        when(vidaService.obterPorId(id)).thenReturn(vida);

        ResponseEntity<?> response = vidaController.obterVidaPorId(id);

        verify(vidaService, times(1)).obterPorId(id);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vida, response.getBody());
    }

    @Test
    public void testObterVidaPorId_NotFoundException() throws Exception {
        Integer id = 1;

        doThrow(new MinisterioRecomecoException(HttpStatus.NOT_FOUND, UtilsConstants.EXCEPTION)).when(vidaService).obterPorId(id);

        ResponseEntity<?> response = vidaController.obterVidaPorId(id);

        verify(vidaService, times(1)).obterPorId(id);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testListarVidas_Success() throws Exception {
        List<Vida> vidas = criarVidasMock(1);

        when(vidaService.listar()).thenReturn(vidas);

        ResponseEntity<?> response = vidaController.listarVidas();

        verify(vidaService, times(1)).listar();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vidas, response.getBody());
    }


    @Test
    public void testAtualizarStatusVidaPorCpf_Success() throws Exception {
        String cpf = UtilsConstants.CPF_VALIDO;
        String status = "Em célula";
        Vida vida = vidaMock();

        when(vidaService.atualizarStatusPorCpf(cpf, status)).thenReturn(vida);

        ResponseEntity<?> response = vidaController.atualizarStatusVidaPorCpf(cpf, status);

        verify(vidaService, times(1)).atualizarStatusPorCpf(cpf, status);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vida, response.getBody());
    }

    @Test
    public void testAtualizarStatusVidaPorCpf_NotFoundException() throws Exception {
        String cpf = UtilsConstants.CPF_INVALIDO;
        String status = "Sem Célula";

        doThrow(new MinisterioRecomecoException(HttpStatus.NOT_FOUND, "Vida not found")).when(vidaService).atualizarStatusPorCpf(cpf, status);

        ResponseEntity<?> response = vidaController.atualizarStatusVidaPorCpf(cpf, status);

        verify(vidaService, times(1)).atualizarStatusPorCpf(cpf, status);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAtualizarStatusVidaPorCpf_Exception() throws Exception {
        String cpf = UtilsConstants.CPF_INVALIDO;
        String status = "Em célula";

        doThrow(new RuntimeException("Internal Server Error")).when(vidaService).atualizarStatusPorCpf(cpf, status);

        ResponseEntity<?> response = vidaController.atualizarStatusVidaPorCpf(cpf, status);

        verify(vidaService, times(1)).atualizarStatusPorCpf(cpf, status);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testListarVidas_Exception() throws Exception {
        doThrow(new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, UtilsConstants.EXCEPTION)).when(vidaService).listar();

        ResponseEntity<?> response = vidaController.listarVidas();

        verify(vidaService, times(1)).listar();
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testListarVidasPorNome_Success() throws Exception {
        String nome = UtilsConstants.NOME_COMPLETO_VALIDO;
        List<Vida> vidas = criarVidasMock(1);

        when(vidaService.listarPorNome(nome)).thenReturn(vidas);

        ResponseEntity<?> response = vidaController.listarVidasPorNome(nome);

        verify(vidaService, times(1)).listarPorNome(nome);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vidas, response.getBody());
    }

    @Test
    public void testListarVidasPorNome_Exception() throws Exception {
        String nome = UtilsConstants.NOME_COMPLETO_INVALIDO;

        doThrow(new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, UtilsConstants.EXCEPTION)).when(vidaService).listarPorNome(nome);

        ResponseEntity<?> response = vidaController.listarVidasPorNome(nome);

        verify(vidaService, times(1)).listarPorNome(nome);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testDeletarVida_Success() throws Exception {
        Integer id = 1;

        doNothing().when(vidaService).deletar(id);

        ResponseEntity<?> response = vidaController.deletarVida(id);

        verify(vidaService, times(1)).deletar(id);
        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
    }

    @Test
    public void testDeletarVida_Exception() throws Exception {
        Integer id = 1;

        doThrow(new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, UtilsConstants.EXCEPTION)).when(vidaService).deletar(id);

        ResponseEntity<?> response = vidaController.deletarVida(id);

        verify(vidaService, times(1)).deletar(id);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
    }

    @Test
    public void testObterVidaPorCpf_Success() throws Exception {
        String cpf = "12345678900";
        Vida vida = vidaMock();

        when(vidaService.obterPorCpf(cpf)).thenReturn(vida);

        ResponseEntity<?> response = vidaController.obterVidaPorCpf(cpf);

        verify(vidaService, times(1)).obterPorCpf(cpf);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vida, response.getBody());
    }

    @Test
    public void testObterVidaPorCpf_NotFoundException() throws Exception {
        String cpf = "12345678900";

        doThrow(new MinisterioRecomecoException(HttpStatus.NOT_FOUND, "Vida not found")).when(vidaService).obterPorCpf(cpf);

        ResponseEntity<?> response = vidaController.obterVidaPorCpf(cpf);

        verify(vidaService, times(1)).obterPorCpf(cpf);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testObterVidaPorCpf_Exception() throws Exception {
        String cpf = "12345678900";

        doThrow(new RuntimeException("Internal Server Error")).when(vidaService).obterPorCpf(cpf);

        ResponseEntity<?> response = vidaController.obterVidaPorCpf(cpf);

        verify(vidaService, times(1)).obterPorCpf(cpf);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testAtualizarStatusVidaPorId_Success() throws Exception {
        Integer id = 1;
        String status = "Em célula";
        Vida vida = vidaMock();

        when(vidaService.atualizarStatusPorId(id, status)).thenReturn(vida);

        ResponseEntity<?> response = vidaController.atualizarStatusVidaPorId(id, status);

        verify(vidaService, times(1)).atualizarStatusPorId(id, status);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vida, response.getBody());
    }

    @Test
    public void testAtualizarStatusVidaPorId_NotFoundException() throws Exception {
        Integer id = 1;
        String status = "Sem Célula";

        doThrow(new MinisterioRecomecoException(HttpStatus.NOT_FOUND, "Vida not found")).when(vidaService).atualizarStatusPorId(id, status);

        ResponseEntity<?> response = vidaController.atualizarStatusVidaPorId(id, status);

        verify(vidaService, times(1)).atualizarStatusPorId(id, status);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testAtualizarStatusVidaPorId_Exception() throws Exception {
        Integer id = 1;
        String status = "Em célula";

        doThrow(new RuntimeException("Internal Server Error")).when(vidaService).atualizarStatusPorId(id, status);

        ResponseEntity<?> response = vidaController.atualizarStatusVidaPorId(id, status);

        verify(vidaService, times(1)).atualizarStatusPorId(id, status);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }

    @Test
    public void testListarVidasPorStatus_Success() throws Exception {
        String status = "Em célula";
        List<Vida> vidas = criarVidasMock(1);

        when(vidaService.listarPorStatus(status)).thenReturn(vidas);

        ResponseEntity<?> response = vidaController.listarVidasPorStatus(status);

        verify(vidaService, times(1)).listarPorStatus(status);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(vidas, response.getBody());
    }

    @Test
    public void testListarVidasPorStatus_NotFoundException() throws Exception {
        String status = "Sem Célula";

        doThrow(new MinisterioRecomecoException(HttpStatus.NOT_FOUND, "Vida não encontrada.")).when(vidaService).listarPorStatus(status);

        ResponseEntity<?> response = vidaController.listarVidasPorStatus(status);

        verify(vidaService, times(1)).listarPorStatus(status);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
    }

    @Test
    public void testListarVidasPorStatus_Exception() throws Exception {
        String status = "Em célula";

        doThrow(new RuntimeException("Internal Server Error")).when(vidaService).listarPorStatus(status);

        ResponseEntity<?> response = vidaController.listarVidasPorStatus(status);

        verify(vidaService, times(1)).listarPorStatus(status);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
    }
}
