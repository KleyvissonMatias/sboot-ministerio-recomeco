package br.com.ministerio.recomeco.domain.dto;

import lombok.*;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Setter
public class Usuario implements Serializable {
    private Integer id;
    private String username;
    private String password;
    private String email;
    private String role;
}
