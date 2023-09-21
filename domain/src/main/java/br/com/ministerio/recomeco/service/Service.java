package br.com.ministerio.recomeco.service;

import java.math.BigInteger;
import java.util.List;

public abstract class Service<T> {
    abstract List<T> listar();
    abstract List<T> listarPorNome(String nome);
    abstract T obterPorId(Integer id);
    abstract void inserir(T t);
    abstract T atualizar(T t);
    abstract void deletar(Integer id);
}
