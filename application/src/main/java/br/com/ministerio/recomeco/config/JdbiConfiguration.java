package br.com.ministerio.recomeco.config;

import br.com.ministerio.recomeco.infrastructure.database.JdbiCelulaRepositoryImpl;
import br.com.ministerio.recomeco.infrastructure.database.JdbiVidaRepositoryImpl;
import br.com.ministerio.recomeco.port.CelulaRepository;
import br.com.ministerio.recomeco.port.VidaRepository;
import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.TransactionAwareDataSourceProxy;

import javax.sql.DataSource;
import java.util.List;

@Configuration
@Slf4j
public class JdbiConfiguration {
    @Bean
    public Jdbi jdbi(DataSource dataSource, List<JdbiPlugin> jdbiPluginList, List<RowMapper<?>> rowMapperList) {
        TransactionAwareDataSourceProxy dataSourceProxy = new TransactionAwareDataSourceProxy(dataSource);
        Jdbi jdbi = Jdbi.create(dataSourceProxy);
        jdbiPluginList.forEach(jdbi::installPlugin);
        rowMapperList.forEach(jdbi::registerRowMapper);

        return jdbi;
    }

    @Bean
    public JdbiPlugin jdbiPlugin() {
        return new SqlObjectPlugin();
    }

    @Bean
    public VidaRepository vidaRepository(Jdbi jdbi) {
        return jdbi.onDemand(JdbiVidaRepositoryImpl.class);
    }

    @Bean
    public CelulaRepository celulaRepository(Jdbi jdbi) {
        return jdbi.onDemand(JdbiCelulaRepositoryImpl.class);
    }
}
