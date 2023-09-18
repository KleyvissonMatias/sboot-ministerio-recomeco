package br.com.ministerio.recomeco.service;

import br.com.ministerio.recomeco.constant.ErroConstants;
import br.com.ministerio.recomeco.domain.dto.Vida;
import br.com.ministerio.recomeco.exception.MinisterioRecomecoException;
import br.com.ministerio.recomeco.port.VidaRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;

import java.math.BigInteger;
import java.util.List;

@RequiredArgsConstructor
public class VidaService implements IService<Vida> {

    private VidaRepository repository;

    private Logger log;

    @Override
    public List<Vida> listar() {
        try {
            List<Vida> vidas = repository.listar();
            if (vidas.isEmpty()) {
                throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
            }
            return vidas;
        } catch (MinisterioRecomecoException e) {
            log.info(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO);
        }
    }

    @Override
    public Vida obterPorId(BigInteger id) {
        try {
            Vida vida = repository.obterPorId(id);
            if (vida == null) {
                throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
            }
            return vida;
        } catch (MinisterioRecomecoException e) {
            log.info(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO);
        }
    }

    @Override
    public void criar(Vida vida) {
        try {
            repository.criar(vida);
        } catch (MinisterioRecomecoException e) {
            log.info(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO);
        }
    }

    @Override
    public Vida atualizar(Vida vida) {
        try {
            Vida vidaAtualizada = repository.atualizar(vida);
            if (vidaAtualizada == null) {
                throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_ATUALIZAR_REGISTRO);
            }
            return vidaAtualizada;
        } catch (MinisterioRecomecoException e) {
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO);
        }
    }

    @Override
    public void deletar(Vida vida) {
        try {
            repository.deletar(vida);
        } catch (MinisterioRecomecoException e) {
            log.info(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO);
        }
    }

    public List<Vida> listarPorNome(String nome) {
        try {
            List<Vida> vidas = repository.listarPorNome(nome);
            if (vidas.isEmpty()) {
                throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
            }
            return vidas;
        } catch (MinisterioRecomecoException e) {
            log.info(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO);
        }
    }

    public Vida obterPorCpf(String cpf) {
        try {
            Vida vida = repository.obterPorCpf(cpf);
            if (vida == null) {
                throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
            }
            return vida;
        } catch (MinisterioRecomecoException e) {
            log.info(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO);
        }
    }

    public Vida atualizarStatus(String cpf, String status) {
        try {
            Vida statusVida = repository.atualizarStatus(cpf, status);
            if (statusVida == null) {
                throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.ERRO_ATUALIZAR_STATUS);
            }
            return statusVida;
        } catch (MinisterioRecomecoException e) {
            log.info(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO);
        }
    }
}
