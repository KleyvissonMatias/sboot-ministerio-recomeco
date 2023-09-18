package br.com.ministerio.recomeco.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpServerErrorException;

@Getter
@Setter
public class MinisterioRecomecoException extends HttpServerErrorException {
    private final HttpStatus status;
    private final String mensagemErro;
    private Object data;

    public MinisterioRecomecoException(HttpStatus status, String message) {
        super(status,message);
        this.status = status;
        this.mensagemErro = message;
    }

    public MinisterioRecomecoException(HttpStatus status, String message, Object data) {
        this(status,message);
        this.data = data;
    }
}
