package br.com.ministerio.recomeco.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class Response implements Serializable {
    private Integer codigo;
    private String retorno;
    private Object erro;

    public Response(Integer codigo, String retorno, Object erro) {
        this.codigo = codigo;
        this.retorno = retorno;
        this.erro = erro;
    }
}
