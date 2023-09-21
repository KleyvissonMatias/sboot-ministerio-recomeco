package br.com.ministerio.recomeco.presentation;

import br.com.ministerio.recomeco.domain.dto.Vida;
import br.com.ministerio.recomeco.domain.response.ErroResponse;
import br.com.ministerio.recomeco.exception.MinisterioRecomecoException;
import br.com.ministerio.recomeco.service.VidaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<?> inserirVida(@RequestBody @Validated Vida vida) {
        try {
            service.inserir(vida);
            return ResponseEntity.ok().build();
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatusCode().value(), e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @PutMapping(path = "/atualizar")
    public ResponseEntity<?> atualizarVida(@RequestBody @Validated Vida vida) {
        try {
            Vida vidaAtualizada = service.atualizar(vida);
            return ResponseEntity.ok(vidaAtualizada);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatusCode().value(), e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @PutMapping(path = "/atualizar-status")
    public ResponseEntity<?> atualizarStatusVida(@RequestParam @Validated String cpf, String status) {
        try {
            Vida statusVida = service.atualizarStatus(cpf, status);
            return ResponseEntity.ok(statusVida);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatusCode().value(), e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @GetMapping(path = "/obter-por-id")
    public ResponseEntity<?> obterVidaPorId(@RequestParam @Validated Integer id) {
        try {
            Vida vida = service.obterPorId(id);
            return ResponseEntity.ok(vida);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatusCode().value(), e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @GetMapping(path = "/obter-por-cpf")
    public ResponseEntity<?> obterVidaPorCpf(@RequestParam @Validated String cpf) {
        try {
            Vida vida = service.obterPorCpf(cpf);
            return ResponseEntity.ok(vida);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatusCode().value(), e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @GetMapping(path = "/listar")
    public ResponseEntity<?> listarVidas() {
        try {
            List<Vida> vidas = service.listar();
            return ResponseEntity.ok(vidas);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatusCode().value(), e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @GetMapping(path = "/listar-por-nome")
    public ResponseEntity<?> listarVidasPorNome(@RequestParam @Validated String nome) {
        try {
            List<Vida> vidas = service.listarPorNome(nome);
            return ResponseEntity.ok(vidas);
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatusCode().value(), e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }

    @DeleteMapping(path = {"/{id}"})
    public ResponseEntity<?> deletarVida(@PathVariable("id") @Validated Integer id) {
        try {
            service.deletar(id);
            return ResponseEntity.noContent().build();
        } catch (MinisterioRecomecoException e) {
            ErroResponse erroResponse = new ErroResponse(e.getStatusCode().value(), e.getMessage());
            return ResponseEntity.status(e.getStatusCode()).body(erroResponse);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            ErroResponse erroResponse = new ErroResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(erroResponse);
        }
    }
}
