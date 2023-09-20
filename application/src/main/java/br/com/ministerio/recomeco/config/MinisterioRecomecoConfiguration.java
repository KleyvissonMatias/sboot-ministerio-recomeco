package br.com.ministerio.recomeco.config;

import br.com.ministerio.recomeco.service.CelulaService;
import br.com.ministerio.recomeco.service.VidaService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinisterioRecomecoConfiguration {
    @Bean
    public VidaService vidaService() {
        return new VidaService();
    }

    @Bean
    public CelulaService celulaService() {
        return new CelulaService();
    }
}
