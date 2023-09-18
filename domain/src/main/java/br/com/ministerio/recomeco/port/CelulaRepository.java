package br.com.ministerio.recomeco.port;

import br.com.ministerio.recomeco.domain.Celula;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CelulaRepository extends IRepository<Celula>{

    List<Celula> listarPorNome(String nome);

}
