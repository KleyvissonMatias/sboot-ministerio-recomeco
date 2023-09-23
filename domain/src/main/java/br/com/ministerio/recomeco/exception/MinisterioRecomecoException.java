package br.com.ministerio.recomeco.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

@Getter
@Setter
@Slf4j
public class MinisterioRecomecoException extends RuntimeException {
    private HttpStatus status;
    private String mensagemErro;
    private Object data;

    public MinisterioRecomecoException(HttpStatus status, String mensagemErro) {
        super(mensagemErro);
        this.status = status;
        this.mensagemErro = mensagemErro;
        logError();
    }

    public MinisterioRecomecoException(HttpStatus status, String mensagemErro, Throwable causa) {
        super(mensagemErro, causa);
        this.status = status;
        this.mensagemErro = mensagemErro;
        logError();
    }

    public MinisterioRecomecoException(HttpStatus status, String mensagemErro, Object data) {
        this(status, mensagemErro);
        this.data = data;
        logError();
    }

    private void logError() {
        log.error("Erro do Ministério Recomeço: Status={}, Mensagem={}, Data={}", status, mensagemErro, data);
    }
}
