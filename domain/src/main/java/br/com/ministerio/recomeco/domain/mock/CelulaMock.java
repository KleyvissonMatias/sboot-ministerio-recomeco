package br.com.ministerio.recomeco.domain.mock;

import br.com.ministerio.recomeco.domain.dto.Celula;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CelulaMock {
    public static List<Celula> criarCelulasMock(int quantidade) {
        return Stream.generate(CelulaMock::criarCelulaMock)
                .limit(quantidade)
                .collect(Collectors.toList());
    }

    public static Celula criarCelulaMock() {
        return Celula.builder()
                .id(1)
                .nomeCelula("Minha Célula")
                .nomeLider("João Líder")
                .diaReuniao("Quarta")
                .horario("19:00")
                .endereco("Rua da Célula, 123")
                .telefoneContato("123-456-7890")
                .outroTelefone("987-654-3210")
                .dataInclusao(LocalDateTime.now())
                .dataAlteracao(LocalDateTime.now())
                .dataExclusao(null)
                .build();
    }
}
