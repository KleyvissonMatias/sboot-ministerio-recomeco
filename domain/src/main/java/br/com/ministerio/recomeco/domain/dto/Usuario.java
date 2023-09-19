package br.com.ministerio.recomeco.domain.dto;

import lombok.*;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class Usuario implements Serializable {
    private Integer id;
}
