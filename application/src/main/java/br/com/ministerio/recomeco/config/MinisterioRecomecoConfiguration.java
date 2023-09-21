package br.com.ministerio.recomeco.config;

import br.com.ministerio.recomeco.service.CelulaIService;
import br.com.ministerio.recomeco.service.VidaIService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MinisterioRecomecoConfiguration {
    @Bean
    public VidaIService vidaService() {
        return new VidaIService();
    }

    @Bean
    public CelulaIService celulaService() {
        return new CelulaIService();
    }
}
