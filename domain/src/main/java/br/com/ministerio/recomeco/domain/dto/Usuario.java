package br.com.ministerio.recomeco.domain.dto;

import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Usuario implements Serializable {
    private Integer id;
    private String login;
    private String senha;
    private String role;
    private LocalDateTime dataInclusao;
    private LocalDateTime dataAlteracao;
    private LocalDateTime dataExclusao;
}
