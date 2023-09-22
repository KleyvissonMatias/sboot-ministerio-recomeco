package br.com.ministerio.recomeco.port;

import java.util.List;

public interface IRepository<T> {
    List<T> listar();
    T obterPorId(Integer id);
    void criar(T t);
    void atualizar(T t);
    void deletar(Integer id);
}
