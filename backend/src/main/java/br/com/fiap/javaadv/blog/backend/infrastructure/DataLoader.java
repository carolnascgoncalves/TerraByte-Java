package br.com.fiap.javaadv.blog.backend.infrastructure;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.*;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Defensivo;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TipoSolo;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.SexoEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.sql.Date;
import java.time.Month;
import java.util.Set;
import java.util.UUID;

@Configuration
public class DataLoader {
    @Autowired
    private PasswordEncoder passwordEncoder;

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


            Defensivo def1 = defensivoRep.save(Defensivo.builder()
                            .id(UUID.fromString("90c87b51-422c-4a4e-83ca-eb73fdc1932a"))
                            .nome("def Teste")
                            .tipo("tp teste")
                            .build());

            Usuario user1 = usuarioRep.save(Usuario.builder()
                            .id(UUID.fromString("978c415d-7c8b-4b37-af9a-d54fcb1bda46"))
                            .nome("Nome teste")
                            .dataNascimento(Date.valueOf("1968-11-14"))
                            .telefone("11945414013")
                            .sexo(SexoEnum.F)
                            .email("emailTeste@gmail.com")
                            .senha(passwordEncoder.encode("12345678"))
                            .urlImg("URLTeste")
                            .build());

            Plantio milho = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("cf795087-0105-4ba9-86e8-6f660a825d92"))
                    .nome("Milho")
                    .tempMin(20)
                    .tempMax(35)
                    .aguaMM(45)
                    .mesesIdeais(Set.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER))
                    .urlImg("https://exemplo.com/milho.png")
                    .build());

            Plantio soja = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("7a075722-d99d-42a1-acc8-9dfc0c50dd18"))
                    .nome("Soja")
                    .tempMin(18)
                    .tempMax(30)
                    .aguaMM(50)
                    .mesesIdeais(Set.of(Month.OCTOBER, Month.NOVEMBER, Month.DECEMBER))
                    .urlImg("https://exemplo.com/soja.png")
                    .build());

            TipoSolo tp1 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("9387c57c-28ec-404b-b505-fbcf86426812"))
                    .nome("AREIA")
                    .build());

            TipoSolo tp2 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("e3164763-d080-4496-8769-34e8628b2f52"))
                    .nome("AREIA_FRANCA")
                    .build());

            TipoSolo tp3 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("f0bf1027-a4ff-481f-8f99-76ad54f62734"))
                    .nome("FRANCO_ARENOSO")
                    .build());

            TipoSolo tp4 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("0022136f-bacf-45d5-837a-a5d0cf7b7394"))
                    .nome("FRANCA")
                    .build());

            TipoSolo tp5 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("2f4bd064-76ac-49c2-9d43-9cd944dec612"))
                    .nome("FRANCO_SILTOSA")
                    .build());

            TipoSolo tp6 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("b3351c2a-2d08-4b63-8d4c-f2961d1f7c9e"))
                    .nome("SILTE")
                    .build());

            TipoSolo tp7 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("abdeafd2-8204-47d1-8ca8-013e47e14cbc"))
                    .nome("FRANCO_ARGILO_ARENOSA")
                    .build());

            TipoSolo tp8 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("b8246fd2-2910-4ac0-b358-8970967eb9b3"))
                    .nome("FRANCO_ARGILOSA")
                    .build());

            TipoSolo tp9 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("a30d31d1-ad34-4ccb-9b50-65f141e913f2"))
                    .nome("FRANCO_ARGILO_SILTOSA")
                    .build());

            TipoSolo tp10 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.randomUUID())
                    .nome("ARGILO_ARENOSA")
                    .build());

            TipoSolo tp11 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.randomUUID())
                    .nome("ARGILA")
                    .build());

            TipoSolo tp12 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.randomUUID())
                    .nome("ARGILO_SILTOSA")
                    .build());

            TipoSolo tp13 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.randomUUID())
                    .nome("MUITO_ARGILOSA")
                    .build());

            TipoSolo tp14 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.randomUUID())
                    .nome("DESCONHECIDO")
                    .build());
        };

    }
}