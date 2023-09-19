package br.com.ministerio.recomeco.presentation;

import br.com.ministerio.recomeco.constant.ErroConstants;
import br.com.ministerio.recomeco.domain.dto.Vida;
import br.com.ministerio.recomeco.domain.response.Response;
import br.com.ministerio.recomeco.exception.MinisterioRecomecoException;
import br.com.ministerio.recomeco.service.VidaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.List;

@RestController
@RequestMapping("v1/api/ministerio-recomeco/vida")
@Slf4j
public class VidaController {

    @Autowired
    private VidaService service;

    @PostMapping(path = "/inserir")
    public ResponseEntity inserirVida(@RequestBody @Validated Vida Vida) {
        try {
            service.inserir(Vida);
            return new ResponseEntity(HttpStatus.CREATED);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/atualizar")
    public ResponseEntity<Vida> atualizarVida(@RequestBody @Validated Vida Vida) {
        try {
            Vida VidaAtualizada = service.atualizar(Vida);
            return new ResponseEntity<Vida>(VidaAtualizada, HttpStatus.OK);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping(path = "/atualizar-status")
    public ResponseEntity<Vida> atualizarStatusVida(@RequestParam @Validated String cpf, String status) {
        try {
            Vida statusVida = service.atualizarStatus(cpf, status);
            return new ResponseEntity<Vida>(statusVida, HttpStatus.OK);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/obter-por-id")
    public ResponseEntity<Vida> obterVidaPorId(@RequestParam @Validated BigInteger id) {
        try {
            Vida Vida = service.obterPorId(id);
            return new ResponseEntity<Vida>(Vida, HttpStatus.OK);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/obter-por-cpf")
    public ResponseEntity<Vida> obterVidaPorCpf(@RequestParam @Validated String cpf) {
        try {
            Vida Vida = service.obterPorCpf(cpf);
            return new ResponseEntity<Vida>(Vida, HttpStatus.OK);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/listar")
    public ResponseEntity<List<Vida>> listarVidas() {
        try {
            List<Vida> Vidas = service.listar();
            return new ResponseEntity<List<Vida>>(Vidas, HttpStatus.OK);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/listar-por-nome")
    public ResponseEntity<List<Vida>> listarVidasPorNome(@RequestParam @Validated String nome) {
        try {
            List<Vida> Vidas = service.listarPorNome(nome);
            return new ResponseEntity<List<Vida>>(Vidas, HttpStatus.OK);
        } catch (MinisterioRecomecoException e) {
            return new ResponseEntity(new Response(e.getStatusCode().value(), e.getMessage(), e), e.getStatusCode());
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return new ResponseEntity(ErroConstants.ERRO_INTERNO, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity deletarVida(@PathVariable("id") @Validated BigInteger id) {
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
