package br.com.ministerio.recomeco.infrastructure.database;

import br.com.ministerio.recomeco.domain.dto.Vida;
import br.com.ministerio.recomeco.port.VidaRepository;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

import java.util.List;

@UseClasspathSqlLocator
@Repository
public interface JdbiVidaRepositoryImpl extends VidaRepository {
    @Override
    @SqlQuery
    @RegisterBeanMapper(Vida.class)
    List<Vida> listar();

    @Override
    @SqlQuery
    @RegisterBeanMapper(Vida.class)
    Vida obterPorId(@Bind("id") Integer id);

    @Override
    @SqlUpdate
    @RegisterBeanMapper(Vida.class)
    void criar(@BindBean Vida vida);

    @Override
    @SqlUpdate
    @RegisterBeanMapper(Vida.class)
    void atualizar(@BindBean Vida vida);

    @Override
    @SqlUpdate
    @RegisterBeanMapper(Vida.class)
    void deletar(@Bind("id") Integer id);

    @Override
    @SqlQuery
    @RegisterBeanMapper(Vida.class)
    Vida obterPorCpf(@Bind("cpf") String cpf);

    @Override
    @SqlQuery
    @RegisterBeanMapper(Vida.class)
    List<Vida> listarPorNome(@Bind("nome") String nome);

    @Override
    @SqlUpdate
    @RegisterBeanMapper(Vida.class)
    void atualizarStatusPorCpf(@Bind("cpf") String cpf, @Bind("status") String status);

    @Override
    @SqlUpdate
    @RegisterBeanMapper(Vida.class)
    void atualizarStatusPorId(@Bind("id") Integer id, @Bind("status") String status);
}
