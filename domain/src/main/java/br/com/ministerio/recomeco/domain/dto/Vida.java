package br.com.ministerio.recomeco.domain.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Vida implements Serializable {
    private Integer id;
    private String nomeCompleto;
    private String cpf;
    private String dataNascimento;
    private String sexo;
    private String estadoCivil;
    private String telefone;
    private String telefoneOutroContato;
    private String endereco;
    private String email;
    private String redeSocial;
    private String possuiCelula;
    private String nomeCelula;
    private String tipoConversao;
    private String campus;
    private String culto;
    private String horarioCulto;
    private String observacao;
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
