package br.com.ministerio.recomeco.domain.mock;

import br.com.ministerio.recomeco.domain.dto.Vida;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static br.com.ministerio.recomeco.constant.UtilsConstants.*;

public class VidaMock {
    public static List<Vida> criarVidasMock(int quantidade) {
        return Stream.generate(VidaMock::vidaMock)
                .limit(quantidade)
                .collect(Collectors.toList());
    }

    public static Vida vidaMock() {
        return Vida.builder()
                .id(1)
                .nomeCompleto(NOME_COMPLETO_VALIDO)
                .culto(CULTO_VALIDO)
                .campus(CAMPUS_VALIDO)
                .cpf(CPF_VALIDO)
                .email(EMAIL_VALIDO)
                .endereco(ENDERECO_VALIDO)
                .telefone(TELEFONE_VALIDO)
                .sexo(SEXO_VALIDO)
                .estadoCivil(ESTADO_CIVIL_VALIDO)
                .status(STATUS)
                .dataNascimento(LocalDate.of(1990, 6, 6))
                .build();
    }
    public static Vida vidaMockInvalido() {
        return Vida.builder()
                .id(1)
                .nomeCompleto(NOME_COMPLETO_INVALIDO)
                .culto(CULTO_INVALIDO)
                .campus(CAMPUS_INVALIDO)
                .cpf(CPF_INVALIDO)
                .email(EMAIL_INVALIDO)
                .endereco(ENDERECO_INVALIDO)
                .telefone(TELEFONE_INVALIDO)
                .sexo(SEXO_INVALIDO)
                .estadoCivil(ESTADO_CIVIL_INVALIDO)
                .status(STATUS_INVALIDO)
                .dataNascimento(LocalDate.of(1990, 6, 6))
                .build();
    }
}
