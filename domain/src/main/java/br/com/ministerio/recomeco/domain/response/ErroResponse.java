package br.com.ministerio.recomeco.domain.response;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Builder
@Getter
@Setter
public class ErroResponse implements Serializable {
    private Integer codigo;
    private String retorno;
    private Object erro;

    public ErroResponse(Integer codigo, String retorno, Object erro) {
        this.codigo = codigo;
        this.retorno = retorno;
        this.erro = erro;
    }
}
