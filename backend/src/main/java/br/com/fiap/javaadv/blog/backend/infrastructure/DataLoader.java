package br.com.fiap.javaadv.blog.backend.infrastructure;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.*;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDataTutPet(
            UsuarioRepository usuarioRep,
            TipoSoloRepository tipoSoloRep,
            PlantioRepository plantioRep,
            EnderecoPlantioRepository enderecoPlantioRep,
            DefensivoRepository defensivoRep,
            AnalisePlantioRepository analisePlantioRep
    ) {
        return args -> {

        };
    }
}