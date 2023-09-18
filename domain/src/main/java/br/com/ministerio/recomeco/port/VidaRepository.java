package br.com.ministerio.recomeco.port;

import br.com.ministerio.recomeco.domain.dto.Vida;

import java.util.List;

public interface VidaRepository extends IRepository<Vida>{

    Vida obterPorCpf(String cpf);

    List<Vida> listarPorNome(String nome);

    Vida atualizarStatus(String cpf,String status);
}
