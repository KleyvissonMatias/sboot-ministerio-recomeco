package br.com.ministerio.recomeco.service;

import br.com.ministerio.recomeco.constant.ErroConstants;
import br.com.ministerio.recomeco.domain.dto.Celula;
import br.com.ministerio.recomeco.exception.MinisterioRecomecoException;
import br.com.ministerio.recomeco.port.CelulaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.math.BigInteger;
import java.util.List;

@Slf4j
public class CelulaService implements IService<Celula> {

    @Autowired
    private CelulaRepository repository;

    @Override
    public List<Celula> listar() {
        try {
            List<Celula> celulas = repository.listar();
            if (celulas.isEmpty()) {
                throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
            }
            return celulas;
        } catch (MinisterioRecomecoException e) {
            log.error(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO, e);
        }
    }

    @Override
    public Celula obterPorId(BigInteger id) {
        try {
            Celula celula = repository.obterPorId(id);
            if (celula == null) {
                throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
            }
            return celula;
        } catch (MinisterioRecomecoException e) {
            log.error(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO, e);
        }
    }

    @Override
    public void inserir(Celula celula) {
        try {
            repository.criar(celula);
        } catch (MinisterioRecomecoException e) {
            log.error(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO, e);
        }
    }

    @Override
    public Celula atualizar(Celula celula) {
        try {
            Celula celulaAtualizada = repository.atualizar(celula);
            if (celulaAtualizada == null) {
                throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_ATUALIZAR_REGISTRO);
            }
            return celulaAtualizada;
        } catch (MinisterioRecomecoException e) {
            log.error(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO, e);
        }
    }

    @Override
    public void deletar(BigInteger id) {
        try {
            repository.deletar(id);
        } catch (MinisterioRecomecoException e) {
            log.error(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO, e);
        }
    }

    public List<Celula> listarPorNome(String nome) {
        try {
            List<Celula> celulas = repository.listarPorNome(nome);
            if (celulas.isEmpty()) {
                throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
            }
            return celulas;
        } catch (MinisterioRecomecoException e) {
            log.error(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO, e);
        }
    }

    public List<Celula> listarPorLider(String lider) {
        try {
            List<Celula> celulas = repository.listarPorLider(lider);
            if (celulas.isEmpty()) {
                throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
            }
            return celulas;
        } catch (MinisterioRecomecoException e) {
            log.error(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO, e);
        }
    }
}
