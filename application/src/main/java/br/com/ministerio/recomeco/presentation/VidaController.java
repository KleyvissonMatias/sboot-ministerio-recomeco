package br.com.ministerio.recomeco.presentation;

import br.com.ministerio.recomeco.constant.Endpoints;
import br.com.ministerio.recomeco.constant.ErroConstants;
import br.com.ministerio.recomeco.domain.dto.Vida;
import br.com.ministerio.recomeco.domain.response.Response;
import br.com.ministerio.recomeco.exception.MinisterioRecomecoException;
import br.com.ministerio.recomeco.service.VidaService;
import lombok.RequiredArgsConstructor;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping(Endpoints.ENDPOINT_VIDA)
@RequiredArgsConstructor
public class VidaController {

    private VidaService service;

    private Logger logger;

    public ResponseEntity inserirVida(@RequestBody @Validated Vida Vida) {
        try {
            service.inserir(Vida);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Vida> atualizarVida(@RequestBody @Validated Vida Vida) {
        try {
            Vida VidaAtualizada = service.atualizar(Vida);
            return new ResponseEntity<Vida>(VidaAtualizada, HttpStatus.OK);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Vida> obterVidaPorId(@RequestParam @Validated BigInteger id) {
        try {
            Vida Vida = service.obterPorId(id);
            return new ResponseEntity<Vida>(Vida, HttpStatus.OK);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Vida>> listarVidas() {
        try {
            List<Vida> Vidas = service.listar();
            return new ResponseEntity<List<Vida>>(Vidas, HttpStatus.OK);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity deletarVida(@RequestBody @Validated Vida Vida) {
        try {
            service.deletar(Vida);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
