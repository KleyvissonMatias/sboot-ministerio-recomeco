package br.com.ministerio.recomeco.infrastructure;

import br.com.ministerio.recomeco.port.CelulaRepository;
import org.jdbi.v3.sqlobject.locator.UseClasspathSqlLocator;
import org.springframework.stereotype.Repository;

@Repository
@UseClasspathSqlLocator
public interface JdbiCelulaRepositoryImpl extends CelulaRepository {

}
