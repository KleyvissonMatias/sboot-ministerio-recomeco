package br.com.ministerio.recomeco.domain.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@Builder
@Getter
@Setter
public class Response implements Serializable {
    private Integer codigo;
    private String retorno;
    private Object erro;
}
