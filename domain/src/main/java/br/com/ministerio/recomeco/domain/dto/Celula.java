package br.com.ministerio.recomeco.domain.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Celula implements Serializable {
    private Integer id;
    private String nomeCelula;
    private String nomeLider;
    private String diaReuniao;
    private String horario;
    private String endereco;
    private String telefoneContato;
    private String outroTelefone;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAlteracao;
    private LocalDateTime dataExclusao;
}
