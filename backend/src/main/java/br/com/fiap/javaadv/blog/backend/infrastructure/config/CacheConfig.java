package br.com.fiap.javaadv.blog.backend.infrastructure.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableCaching
public class CacheConfig {

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(

                // Usuario
                "usuarioCache",
                "usuarioListCache",
                "usuarioByEmailCache",

                // Tipo Solo
                "tipoSoloByIdCache",
                "tipoSoloListCache",

                // Plantio
                "plantioByIdCache",
                "plantioListCache",
                "plantioTipoSoloCache",
                "plantioDefensivoCache",

                // Endereco
                "enderecoCache",
                "enderecoListCache",
                "enderecoUsuarioCache",

                // Defensivo
                "defensivoByIdCache",
                "defensivoListCache",
                "defensivoTipoCache",

                // Analise
                "analiseByIdCache",
                "analiseListCache",
                "analiseUsuarioCache",
                "analiseUsuarioIdCache",

                // APIs externas
                "geocodingCache",
                "soloCache",
                "cepCache",
                "weatherCache"
        );
    }

}