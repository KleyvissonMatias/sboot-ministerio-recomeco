package br.com.ministerio.recomeco.presentation;

import br.com.ministerio.recomeco.constant.ErroConstants;
import br.com.ministerio.recomeco.domain.dto.Celula;
import br.com.ministerio.recomeco.domain.response.Response;
import br.com.ministerio.recomeco.exception.MinisterioRecomecoException;
import br.com.ministerio.recomeco.service.CelulaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("v1/api/ministerio-recomeco/celula")
@Slf4j
public class CelulaController {

    @Autowired
    private CelulaService service;

    @PostMapping(path = "/inserir")
    public ResponseEntity inserirCelula(@RequestBody @Validated Celula celula) {
        try {
            service.inserir(celula);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/atualizar")
    public ResponseEntity<Celula> atualizarCelula(@RequestBody @Validated Celula celula) {
        try {
            Celula celulaAtualizada = service.atualizar(celula);
            return new ResponseEntity<Celula>(celulaAtualizada, HttpStatus.OK);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/obter-por-id")
    public ResponseEntity<Celula> obterCelulaPorId(@RequestParam @Validated BigInteger id) {
        try {
            Celula celula = service.obterPorId(id);
            return new ResponseEntity<Celula>(celula, HttpStatus.OK);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/listar")
    public ResponseEntity<List<Celula>> listarCelulas() {
        try {
            List<Celula> celulas = service.listar();
            return new ResponseEntity<List<Celula>>(celulas, HttpStatus.OK);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/listar-por-nome")
    public ResponseEntity<List<Celula>> listarCelulasPorNome(@RequestParam @Validated String nome) {
        try {
            List<Celula> celulas = service.listarPorNome(nome);
            return new ResponseEntity<List<Celula>>(celulas, HttpStatus.OK);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/listar-por-lider")
    public ResponseEntity<List<Celula>> listarCelulasPorLider(@RequestParam @Validated String lider) {
        try {
            List<Celula> celulas = service.listarPorLider(lider);
            return new ResponseEntity<List<Celula>>(celulas, HttpStatus.OK);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity deletarCelula(@PathVariable("id") BigInteger id) {
        try {
            service.deletar(id);
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
