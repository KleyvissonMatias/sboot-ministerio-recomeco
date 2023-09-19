package br.com.ministerio.recomeco.port;

import java.math.BigInteger;
import java.util.List;

public interface IRepository<T> {
    List<T> listar();
    T obterPorId(BigInteger id);
    void criar(T t);
    T atualizar(T t);
    void deletar(BigInteger id);
}
