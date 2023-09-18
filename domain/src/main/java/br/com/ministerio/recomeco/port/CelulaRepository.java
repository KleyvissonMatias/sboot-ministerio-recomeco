package br.com.ministerio.recomeco.port;

import br.com.ministerio.recomeco.domain.dto.Celula;

import java.util.List;

public interface CelulaRepository extends IRepository<Celula>{

    List<Celula> listarPorNome(String nome);

    List<Celula> listarPorLider(String nome);

}
