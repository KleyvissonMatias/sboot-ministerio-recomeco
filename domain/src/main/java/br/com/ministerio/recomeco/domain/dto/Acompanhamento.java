package br.com.ministerio.recomeco.domain.dto;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Acompanhamento implements Serializable {
    private BigInteger id;
    private String liderCelula;
    private String liderTreinamento;
    private LocalDate dataBatismo;
    private LocalDate dataEncontro;
    private LocalDate dataRenovo;
    private LocalDate dataAguias;
    private LocalDate dataLidereUm;
    private LocalDate dataLidereDois;
    private LocalDate dataInclusao;
    private LocalDate dataAlteracao;
    private LocalDate dataExclusao;
}
