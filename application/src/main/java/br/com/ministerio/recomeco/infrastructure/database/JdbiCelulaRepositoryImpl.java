package br.com.ministerio.recomeco.infrastructure.database;

import br.com.ministerio.recomeco.domain.dto.Celula;
import br.com.ministerio.recomeco.domain.dto.Vida;
import br.com.ministerio.recomeco.port.CelulaRepository;
import org.jdbi.v3.sqlobject.config.RegisterBeanMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.customizer.BindBean;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.jdbi.v3.sqlobject.statement.SqlQuery;
import org.jdbi.v3.sqlobject.statement.SqlUpdate;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.util.List;

@UseClasspathSqlLocator
@Repository
public interface JdbiCelulaRepositoryImpl extends CelulaRepository {

    @Override
    @SqlQuery
    @RegisterBeanMapper(Celula.class)
    List<Celula> listarPorNome(@Bind("nome") String nome);

    @Override
    @SqlQuery
    @RegisterBeanMapper(Celula.class)
    List<Celula> listarPorLider(@Bind("nome") String nome);

    @Override
    @SqlQuery
    @RegisterBeanMapper(Celula.class)
    List<Celula> listar();

    @Override
    @SqlQuery
    @RegisterBeanMapper(Celula.class)
    Celula obterPorId(@Bind("id") BigInteger id);

    @Override
    @SqlUpdate
    @RegisterBeanMapper(Celula.class)
    void criar(@BindBean Celula celula);

    @Override
    @SqlQuery
    @RegisterBeanMapper(Vida.class)
    Celula atualizar(@BindBean Celula celula);

    @Override
    @SqlUpdate
    @RegisterBeanMapper(Celula.class)
    void deletar(@BindBean Celula celula);
}
