package br.com.ministerio.recomeco.service;

import br.com.ministerio.recomeco.domain.dto.Vida;
import br.com.ministerio.recomeco.port.VidaRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import static br.com.ministerio.recomeco.domain.mock.VidaMock.vidaMock;
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
        when(this.service.obterPorId(1)).thenReturn(vidaRetorno);
        when(this.service.atualizar(vidaRetorno)).thenReturn(vidaRetorno);

        Vida vida = this.service.atualizar(vidaRetorno);
        Assertions.assertNotNull(vida);
    }

    @Test
    public void deletarTesteRetornoComSucesso() {
        Vida vidaMock = vidaMock();
        this.service.deletar(vidaMock.getId());
        verify(this.repository, times(1)).deletar(vidaMock.getId());
    }
}
