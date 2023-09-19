package br.com.ministerio.recomeco.service;

import java.math.BigInteger;
import java.util.List;

public interface IService <T> {
    List<T> listar();
    T obterPorId(BigInteger id);
    void inserir(T t);
    T atualizar(T t);
    void deletar(BigInteger id);
}
