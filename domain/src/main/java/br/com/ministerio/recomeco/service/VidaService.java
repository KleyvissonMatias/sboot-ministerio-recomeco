package br.com.ministerio.recomeco.service;

import br.com.ministerio.recomeco.constant.ErroConstants;
import br.com.ministerio.recomeco.domain.Vida;
import br.com.ministerio.recomeco.exception.MinisterioRecomecoException;
import br.com.ministerio.recomeco.port.VidaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import java.math.BigInteger;
import java.util.List;

@Service
public class VidaService implements IService<Vida> {

    @Autowired
    private VidaRepository repository;

    @Override
    public List<Vida> listar() {
        try {
            List<Vida> vidas = repository.listar();
            if (vidas.isEmpty()) {
                throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
            }
            return vidas;
        } catch (HttpServerErrorException e) {
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO);
        } catch (Exception e) {
            throw new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, ErroConstants.ERRO_INTERNO, e);
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
        } catch (HttpServerErrorException e) {
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO);
        } catch (Exception e) {
            throw new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, ErroConstants.ERRO_INTERNO, e);
        }
    }

    @Override
    public void criar(Vida vida) {
        try {
            repository.criar(vida);
        } catch (HttpServerErrorException e) {
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO);
        } catch (Exception e) {
            throw new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, ErroConstants.ERRO_INTERNO, e);
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
        } catch (HttpServerErrorException e) {
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO);
        } catch (Exception e) {
            throw new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, ErroConstants.ERRO_INTERNO, e);
        }
    }

    @Override
    public void deletar(Vida vida) {
        try {
            repository.deletar(vida);
        } catch (HttpServerErrorException e) {
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO);
        } catch (Exception e) {
            throw new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, ErroConstants.ERRO_INTERNO, e);
        }
    }

    public List<Vida> listarPorNome(String nome) {
        try {
            List<Vida> vidas = repository.listarPorNome(nome);
            if (vidas.isEmpty()) {
                throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
            }
            return vidas;
        } catch (HttpServerErrorException e) {
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO);
        } catch (Exception e) {
            throw new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, ErroConstants.ERRO_INTERNO, e);
        }
    }

    public Vida obterPorCpf(String cpf) {
        try {
            Vida vida = repository.obterPorCpf(cpf);
            if (vida == null) {
                throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
            }
            return vida;
        } catch (HttpServerErrorException e) {
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO);
        } catch (Exception e) {
            throw new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, ErroConstants.ERRO_INTERNO, e);
        }
    }

    public Vida atualizarStatus(String cpf, String status) {
        try {
            Vida statusVida = repository.atualizarStatus(cpf, status);
            if (statusVida == null) {
                throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.ERRO_ATUALIZAR_STATUS);
            }
            return statusVida;
        } catch (HttpServerErrorException e) {
            throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_NEGOCIO);
        } catch (Exception e) {
            throw new MinisterioRecomecoException(HttpStatus.INTERNAL_SERVER_ERROR, ErroConstants.ERRO_INTERNO, e);
        }
    }
}
