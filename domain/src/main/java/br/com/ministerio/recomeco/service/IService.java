package br.com.ministerio.recomeco.service;

import java.util.List;

public interface IService<T> {
     List<T> listar();
     List<T> listarPorNome(String nome);
     T obterPorId(Integer id);
     void inserir(T t);
     T atualizar(T t);
     void deletar(Integer id);
}
