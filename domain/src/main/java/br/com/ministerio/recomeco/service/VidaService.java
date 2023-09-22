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

    private void handleError(MinisterioRecomecoException e) {
        log.error(ErroConstants.ERRO_NEGOCIO, e.getStatusCode(), e.getMessage());
        throw e;
    }

    @Override
    public List<Vida> listar() {
        List<Vida> vidas = repository.listar();
        if (vidas.isEmpty()) {
            handleError(new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO));
        }
        return vidas;
    }

    @Override
    public Vida obterPorId(Integer id) {
        Vida vida = repository.obterPorId(id);
        if (vida == null) {
            handleError(new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO));
        }
        return vida;
    }

    @Override
    public void inserir(Vida vida) {
        repository.criar(vida);
    }

    public Vida atualizar(Vida vida) {
        repository.atualizar(vida);
        return vida;
    }

    public void deletar(Integer id) {
        Vida vida = obterPorId(id);
        if (vida == null) {
            handleError(new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.ERRO_EXCLUIR_REGISTRO));
        }
        repository.deletar(id);
    }

    @Override
    public List<Vida> listarPorNome(String nome) {
        List<Vida> vidas = repository.listarPorNome(nome);
        if (vidas.isEmpty()) {
            handleError(new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO));
        }
        return vidas;
    }

    public List<Vida> listarPorStatus(String status) {
        List<Vida> vidas = repository.listarPorStatus(status);
        if (vidas.isEmpty()) {
            handleError(new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO));
        }
        return vidas;
    }

    public Vida obterPorCpf(String cpf) {
        Vida vida = repository.obterPorCpf(cpf);
        if (vida == null) {
            handleError(new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO));
        }
        return vida;
    }

    public Vida atualizarStatusPorCpf(String cpf, String status) {
        Vida statusVida = obterPorCpf(cpf);

        if (statusVida != null) {
            statusVida.setStatus(status);
            repository.atualizar(statusVida);
        }
        return statusVida;
    }

    public Vida atualizarStatusPorId(Integer id, String status) {
        Vida statusVida = obterPorId(id);

        if (statusVida != null) {
            statusVida.setStatus(status);
            repository.atualizar(statusVida);
        }
        return statusVida;
    }
}
