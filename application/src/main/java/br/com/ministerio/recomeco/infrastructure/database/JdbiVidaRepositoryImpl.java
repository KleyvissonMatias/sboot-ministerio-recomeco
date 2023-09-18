package br.com.ministerio.recomeco.infrastructure.database;

import br.com.ministerio.recomeco.port.VidaRepository;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.springframework.stereotype.Repository;

@Repository
@UseClasspathSqlLocator
public interface JdbiVidaRepositoryImpl extends VidaRepository {

}
