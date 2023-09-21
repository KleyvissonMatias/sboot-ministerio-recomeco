package br.com.ministerio.recomeco.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Celula implements Serializable {
    private Integer id;

    @NotNull(message = "O nome da célula não pode ser nulo")
    @NotBlank(message = "O nome da célula não pode estar em branco")
    private String nomeCelula;

    @NotNull(message = "O nome do líder não pode ser nulo")
    @NotBlank(message = "O nome do líder não pode estar em branco")
    private String nomeLider;

    @NotNull(message = "O dia de reunião não pode ser nulo")
    @Pattern(regexp = "^(Domingo|Segunda|Terça|Quarta|Quinta|Sexta|Sábado)$", message = "Dia de reunião inválido.")
    private String diaReuniao;

    @NotNull(message = "O horário da célula não pode ser nulo")
    @Pattern(regexp = "^\\d{2}:\\d{2}$", message = "Formato de horário inválido Use HH:mm")
    private String horario;

    @NotNull(message = "O endereço não pode ser nulo")
    @NotBlank(message = "O endereço não pode estar em branco")
    private String endereco;

    @NotNull(message = "O Telefone não pode ser nulo")
    @NotBlank(message = "O Telefone não pode estar em branco")
    private String telefoneContato;

    private String outroTelefone;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataInclusao;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataAlteracao;

    @DateTimeFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime dataExclusao;
}
