package br.com.ministerio.recomeco.service;

import br.com.ministerio.recomeco.constant.ErroConstants;
import br.com.ministerio.recomeco.domain.dto.Vida;
import br.com.ministerio.recomeco.exception.MinisterioRecomecoException;
import br.com.ministerio.recomeco.port.VidaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

@Slf4j
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
        } catch (MinisterioRecomecoException e) {
            log.error(ErroConstants.ERRO_NEGOCIO, e.getStatus(), e.getMensagemErro());
            throw new MinisterioRecomecoException(e.getStatus(), e.getMensagemErro(), e.getData());
        }
    }

    @Override
    public Vida obterPorId(Integer id) {
        try {
            Vida vida = repository.obterPorId(id);
            if (vida == null) {
                throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
            }
            return vida;
        } catch (MinisterioRecomecoException e) {
            log.error(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(e.getStatus(), e.getMensagemErro(), e.getData());
        }
    }

    @Override
    public void inserir(Vida vida) {
        try {
            repository.criar(vida);
        } catch (MinisterioRecomecoException e) {
            log.error(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(e.getStatus(), e.getMensagemErro(), e.getData());
        }
    }

    @Override
    public Vida atualizar(Vida vida) {
        try {
            repository.atualizar(vida);
            Vida vidaAtualizada = obterPorId(vida.getId());
            if (vidaAtualizada == null) {
                throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_ATUALIZAR_REGISTRO);
            }
            return vidaAtualizada;
        } catch (MinisterioRecomecoException e) {
            throw new MinisterioRecomecoException(e.getStatus(), e.getMensagemErro(), e.getData());
        }
    }

    @Override
    public void deletar(Integer id) {
        try {
            repository.deletar(id);
        } catch (MinisterioRecomecoException e) {
            log.error(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(e.getStatus(), e.getMensagemErro(), e.getData());
        }
    }

    @Override
    public List<Vida> listarPorNome(String nome) {
        try {
            List<Vida> vidas = repository.listarPorNome(nome);
            if (vidas.isEmpty()) {
                throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
            }
            return vidas;
        } catch (MinisterioRecomecoException e) {
            log.error(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(e.getStatus(), e.getMensagemErro(), e.getData());
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
            log.error(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(e.getStatus(), e.getMensagemErro(), e.getData());
        }
    }

    public Vida atualizarStatusPorCpf(String cpf, String status) {
        try {
            repository.atualizarStatusPorCpf(cpf, status);
            Vida statusVida = obterPorCpf(cpf);
            if (statusVida == null) {
                throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_ATUALIZAR_STATUS);
            }
            return statusVida;
        } catch (MinisterioRecomecoException e) {
            log.error(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(e.getStatus(), e.getMensagemErro(), e.getData());
        }
    }

    public Vida atualizarStatusPorId(Integer id, String status) {
        try {
            repository.atualizarStatusPorId(id, status);
            Vida statusVida = obterPorId(id);
            if (statusVida == null) {
                throw new MinisterioRecomecoException(HttpStatus.BAD_REQUEST, ErroConstants.ERRO_ATUALIZAR_STATUS);
            }
            return statusVida;
        } catch (MinisterioRecomecoException e) {
            log.error(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
            throw new MinisterioRecomecoException(e.getStatus(), e.getMensagemErro(), e.getData());
        }
    }
}
