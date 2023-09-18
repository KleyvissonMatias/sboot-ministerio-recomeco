package br.com.ministerio.recomeco.presentation;

import br.com.ministerio.recomeco.constant.Endpoints;
import br.com.ministerio.recomeco.constant.ErroConstants;
import br.com.ministerio.recomeco.domain.dto.Celula;
import br.com.ministerio.recomeco.domain.response.Response;
import br.com.ministerio.recomeco.exception.MinisterioRecomecoException;
import br.com.ministerio.recomeco.service.CelulaService;
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
@RequestMapping(Endpoints.ENDPOINT_CELULA)
@RequiredArgsConstructor
public class CelulaController {

    private CelulaService service;
    private Logger logger;

    public ResponseEntity inserirCelula(@RequestBody @Validated Celula celula) {
        try {
            service.inserir(celula);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Celula> atualizarCelula(@RequestBody @Validated Celula celula) {
        try {
            Celula celulaAtualizada = service.atualizar(celula);
            return new ResponseEntity<Celula>(celulaAtualizada, HttpStatus.OK);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Celula> obterCelulaPorId(@RequestParam @Validated BigInteger id) {
        try {
            Celula celula = service.obterPorId(id);
            return new ResponseEntity<Celula>(celula, HttpStatus.OK);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<List<Celula>> listarCelulas() {
        try {
            List<Celula> celulas = service.listar();
            return new ResponseEntity<List<Celula>>(celulas, HttpStatus.OK);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity deletarCelula(@RequestBody @Validated Celula celula) {
        try {
            service.deletar(celula);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            logger.error(e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
