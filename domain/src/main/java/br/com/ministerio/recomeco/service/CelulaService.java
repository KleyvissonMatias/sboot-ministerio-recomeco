package br.com.ministerio.recomeco.service;

import br.com.ministerio.recomeco.constant.ErroConstants;
import br.com.ministerio.recomeco.domain.Celula;
import br.com.ministerio.recomeco.exception.MinisterioRecomecoException;
import br.com.ministerio.recomeco.port.CelulaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.math.BigInteger;
import java.util.List;

@Service
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
        } catch (HttpServerErrorException e) {
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO, e);
        } catch (Exception e) {
            throw new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, ErroConstants.ERRO_INTERNO, e);
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
        } catch (HttpServerErrorException e) {
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO, e);
        } catch (Exception e) {
            throw new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, ErroConstants.ERRO_INTERNO, e);
        }
    }

    @Override
    public void criar(Celula celula) {
        try {
            repository.criar(celula);
        } catch (HttpServerErrorException e) {
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO, e);
        } catch (Exception e) {
            throw new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, ErroConstants.ERRO_INTERNO, e);
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
        } catch (HttpServerErrorException e) {
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO, e);
        } catch (Exception e) {
            throw new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, ErroConstants.ERRO_INTERNO, e);
        }
    }

    @Override
    public void deletar(Celula celula) {
        try {
            repository.deletar(celula);
        } catch (HttpServerErrorException e) {
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO, e);
        } catch (Exception e) {
            throw new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, ErroConstants.ERRO_INTERNO, e);
        }
    }

    public List<Celula> listarPorNome(String nome) {
        try {
            List<Celula> celulas = repository.listarPorNome(nome);
            if (celulas.isEmpty()) {
                throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
            }
            return celulas;
        } catch (HttpServerErrorException e) {
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO, e);
        } catch (Exception e) {
            throw new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, ErroConstants.ERRO_INTERNO, e);
        }
    }
}
