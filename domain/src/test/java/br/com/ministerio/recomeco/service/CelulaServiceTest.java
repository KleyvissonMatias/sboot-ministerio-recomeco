package br.com.ministerio.recomeco.service;

import br.com.ministerio.recomeco.port.CelulaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class CelulaServiceTest {
    @Mock
    private CelulaRepository repository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void inserirTesteRetornoComSucesso() {

    }
}
