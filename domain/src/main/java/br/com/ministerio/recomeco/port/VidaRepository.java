package br.com.ministerio.recomeco.port;

import br.com.ministerio.recomeco.domain.Vida;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VidaRepository extends IRepository<Vida>{

    Vida obterPorCpf(String cpf);

    List<Vida> listarPorNome(String nome);

    Vida atualizarStatus(String cpf,String status);
}
