package br.com.ministerio.recomeco.port;

import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@Repository
public interface IRepository<T> {
    List<T> listar();
    T obterPorId(BigInteger id);
    void criar(T t);
    T atualizar(T t);
    void deletar(T t);
}
