package br.com.ministerio.recomeco.config;

import lombok.extern.slf4j.Slf4j;
import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.spi.JdbiPlugin;
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
}
