package br.com.ministerio.recomeco.presentation;

import br.com.ministerio.recomeco.constant.ErroConstants;
import br.com.ministerio.recomeco.domain.dto.Vida;
import br.com.ministerio.recomeco.domain.response.ErroResponse;
import br.com.ministerio.recomeco.exception.MinisterioRecomecoException;
import br.com.ministerio.recomeco.service.VidaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("v1/api/ministerio-recomeco/vida")
@Slf4j
public class VidaController {
    @Autowired
    private VidaService service;

    @PostMapping(path = "/inserir")
    @Secured("ROLE_USER")
    public ResponseEntity<?> inserirVida(@RequestBody @Validated Vida vida) {
        try {
            service.inserir(vida);
            return ResponseEntity.ok().build();
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatus().value(), e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErroConstants.ERRO_INTERNO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @PutMapping(path = "/atualizar")
    @Secured("ROLE_USER")
    public ResponseEntity<?> atualizarVida(@RequestBody @Validated Vida vida) {
        try {
            Vida vidaAtualizada = service.atualizar(vida);
            return ResponseEntity.ok(vidaAtualizada);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatus().value(), e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErroConstants.ERRO_INTERNO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @PatchMapping(path = "/atualizar-status-por-cpf")
    @Secured("ROLE_USER")
    public ResponseEntity<?> atualizarStatusVidaPorCpf(@RequestParam @Validated String cpf, String status) {
        try {
            Vida statusVida = service.atualizarStatusPorCpf(cpf, status);
            return ResponseEntity.ok(statusVida);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatus().value(), e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErroConstants.ERRO_INTERNO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @PatchMapping(path = "/atualizar-status-por-id")
    @Secured("ROLE_USER")
    public ResponseEntity<?> atualizarStatusVidaPorId(@RequestParam @Validated Integer id, String status) {
        try {
            Vida statusVida = service.atualizarStatusPorId(id, status);
            return ResponseEntity.ok(statusVida);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatus().value(), e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErroConstants.ERRO_INTERNO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @GetMapping(path = "/obter-por-id")
    @Secured("ROLE_USER")
    public ResponseEntity<?> obterVidaPorId(@RequestParam @Validated Integer id) {
        try {
            Vida vida = service.obterPorId(id);
            return ResponseEntity.ok(vida);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatus().value(), e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErroConstants.ERRO_INTERNO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @GetMapping(path = "/obter-por-cpf")
    @Secured("ROLE_USER")
    public ResponseEntity<?> obterVidaPorCpf(@RequestParam @Validated String cpf) {
        try {
            Vida vida = service.obterPorCpf(cpf);
            return ResponseEntity.ok(vida);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatus().value(), e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErroConstants.ERRO_INTERNO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @GetMapping(path = "/listar")
    @Secured("ROLE_USER")
    public ResponseEntity<?> listarVidas() {
        try {
            List<Vida> vidas = service.listar();
            return ResponseEntity.ok(vidas);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatus().value(), e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErroConstants.ERRO_INTERNO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @GetMapping(path = "/listar-por-nome")
    @Secured("ROLE_USER")
    public ResponseEntity<?> listarVidasPorNome(@RequestParam @Validated String nome) {
        try {
            List<Vida> vidas = service.listarPorNome(nome);
            return ResponseEntity.ok(vidas);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatus().value(), e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErroConstants.ERRO_INTERNO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @GetMapping(path = "/listar-por-status")
    @Secured("ROLE_USER")
    public ResponseEntity<?> listarVidasPorStatus(@RequestParam @Validated String status) {
        try {
            List<Vida> vidas = service.listarPorStatus(status);
            return ResponseEntity.ok(vidas);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatus().value(), e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErroConstants.ERRO_INTERNO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @DeleteMapping(path = {"/{id}"})
    @Secured("ROLE_USER")
    public ResponseEntity<?> deletarVida(@PathVariable("id") @Validated Integer id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatus().value(), e.getMessage());
            return ResponseEntity.status(e.getStatus()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), ErroConstants.ERRO_INTERNO);
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }
}
