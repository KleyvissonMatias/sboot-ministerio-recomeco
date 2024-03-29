package br.com.ministerio.recomeco.presentation;

import br.com.ministerio.recomeco.constant.ErroConstants;
import br.com.ministerio.recomeco.domain.dto.Celula;
import br.com.ministerio.recomeco.domain.response.ErroResponse;
import br.com.ministerio.recomeco.exception.MinisterioRecomecoException;
import br.com.ministerio.recomeco.service.CelulaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/ministerio-recomeco/celula")
@Slf4j
public class CelulaController {
    @Autowired
    private CelulaService service;

    @PostMapping(path = "/inserir")
    @Secured("ROLE_USER")
    public ResponseEntity<?> inserirCelula(@RequestBody @Validated Celula celula) {
        try {
            service.inserir(celula);
            return ResponseEntity.ok().build();
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatus().value(), e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(erroResponse);
        } catch (Exception e) {
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErroConstants.ERRO_INTERNO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @PutMapping(path = "/atualizar")
    @Secured("ROLE_USER")
    public ResponseEntity<?> atualizarCelula(@RequestBody @Validated Celula celula) {
        try {
            Celula celulaAtualizada = service.atualizar(celula);
            return ResponseEntity.ok(celulaAtualizada);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatus().value(), e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(erroResponse);
        } catch (Exception e) {
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErroConstants.ERRO_INTERNO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @GetMapping(path = "/obter-por-id")
    @Secured("ROLE_USER")
    public ResponseEntity<?> obterCelulaPorId(@RequestParam @Validated Integer id) {
        try {
            Celula celula = service.obterPorId(id);
            return ResponseEntity.ok(celula);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatus().value(), e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(erroResponse);
        } catch (Exception e) {
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErroConstants.ERRO_INTERNO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @GetMapping(path = "/listar")
    @Secured("ROLE_USER")
    public ResponseEntity<?> listarCelulas() {
        try {
            List<Celula> celulas = service.listar();
            return ResponseEntity.ok(celulas);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatus().value(), e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(erroResponse);
        } catch (Exception e) {
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErroConstants.ERRO_INTERNO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @GetMapping(path = "/listar-por-nome")
    @Secured("ROLE_USER")
    public ResponseEntity<?> listarCelulasPorNome(@RequestParam @Validated String nome) {
        try {
            List<Celula> celulas = service.listarPorNome(nome);
            return ResponseEntity.ok(celulas);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatus().value(), e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(erroResponse);
        } catch (Exception e) {
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErroConstants.ERRO_INTERNO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @GetMapping(path = "/listar-por-lider")
    @Secured("ROLE_USER")
    public ResponseEntity<?> listarCelulasPorLider(@RequestParam @Validated String lider) {
        try {
            List<Celula> celulas = service.listarPorLider(lider);
            return ResponseEntity.ok(celulas);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatus().value(), e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(erroResponse);
        } catch (Exception e) {
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErroConstants.ERRO_INTERNO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @DeleteMapping(path = {"/{id}"})
    @Secured("ROLE_USER")
    public ResponseEntity<?> deletarCelula(@PathVariable("id") Integer id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatus().value(), e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(erroResponse);
        } catch (Exception e) {
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErroConstants.ERRO_INTERNO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }
}
