package br.com.ministerio.recomeco.service;

import br.com.ministerio.recomeco.constant.ErroConstants;
import br.com.ministerio.recomeco.domain.dto.Celula;
import br.com.ministerio.recomeco.exception.MinisterioRecomecoException;
import br.com.ministerio.recomeco.port.CelulaRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import java.util.List;

@Slf4j
public class CelulaService implements IService<Celula> {

    @Autowired
    private CelulaRepository repository;

    @Override
    public List<Celula> listar() {
        List<Celula> celulas = repository.listar();
        if (celulas.isEmpty()) {
            throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
        }
        return celulas;
    }

    @Override
    public Celula obterPorId(Integer id) {
        Celula celula = repository.obterPorId(id);
        if (celula == null) {
            throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
        }
        return celula;
    }

    @Override
    public void inserir(Celula celula) {
        repository.criar(celula);
    }

    public Celula atualizar(Celula celula) {
        obterPorId(celula.getId());
        repository.atualizar(celula);
        return celula;
    }

    public void deletar(Integer id) {
        obterPorId(id);
        repository.deletar(id);
    }

    @Override
    public List<Celula> listarPorNome(String nome) {
        List<Celula> celulas = repository.listarPorNome(nome);
        if (celulas.isEmpty()) {
            throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
        }
        return celulas;
    }

    public List<Celula> listarPorLider(String lider) {
        List<Celula> celulas = repository.listarPorLider(lider);
        if (celulas.isEmpty()) {
            throw new MinisterioRecomecoException(HttpStatus.NOT_FOUND, ErroConstants.SEM_REGISTRO);
        }
        return celulas;
    }
}
