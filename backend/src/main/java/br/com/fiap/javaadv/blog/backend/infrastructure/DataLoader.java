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
                    .id(UUID.fromString("92c2e329-9347-4ec7-9694-9dced263ee2f"))
                    .nome("ARGILO_ARENOSA")
                    .build());

            TipoSolo tp11 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("19ecfccf-79b2-4fae-8f9c-c7cd1a0684c4"))
                    .nome("ARGILA")
                    .build());

            TipoSolo tp12 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("327f9f42-5403-4749-9fb5-e30d4fce120b"))
                    .nome("ARGILO_SILTOSA")
                    .build());

            TipoSolo tp13 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("94ee033b-0d04-4b59-84f8-689b0f221af3"))
                    .nome("MUITO_ARGILOSA")
                    .build());

            TipoSolo tp14 = tipoSoloRep.save(TipoSolo.builder()
                    .id(UUID.fromString("da20088c-dab3-42f6-8b43-6121141a99c8"))
                    .nome("DESCONHECIDO")
                    .build());


            Defensivo glifosato = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("f3d9d6d6-49f2-4e8f-b7d6-3b94a6b7f001"))
                    .nome("Glifosato")
                    .tipo("HERBICIDA")
                    .build());

            Defensivo atrazina = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("c0e4e6b1-6d27-49f8-97f0-8d2d2d3f0002"))
                    .nome("Atrazina")
                    .tipo("HERBICIDA")
                    .build());

            Defensivo mancozebe = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("d9b84f71-f52f-43df-a5e7-1c63e0cf0003"))
                    .nome("Mancozebe")
                    .tipo("FUNGICIDA")
                    .build());

            Defensivo clorpirifos = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("7f0f35a4-4d1c-45f5-a2f8-3e9f1ab00004"))
                    .nome("Clorpirifós")
                    .tipo("INSETICIDA")
                    .build());

            Defensivo paraquate = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("aa17eb96-8b6d-45c8-8fd2-54bfe7d00005"))
                    .nome("Paraquate")
                    .tipo("HERBICIDA")
                    .build());

            Defensivo imidacloprido = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("eb69a71c-c2b4-4d92-aac5-58db4e800006"))
                    .nome("Imidacloprido")
                    .tipo("INSETICIDA")
                    .build());

            Defensivo tebuconazol = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("f84656e1-1d42-46e8-a1f6-7bc5ec500007"))
                    .nome("Tebuconazol")
                    .tipo("FUNGICIDA")
                    .build());

            Defensivo fipronil = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("2c57a08a-0a0f-4a24-9d65-89b6cf900008"))
                    .nome("Fipronil")
                    .tipo("INSETICIDA")
                    .build());

            Defensivo doisQuatroD = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("9f4f49df-c39d-4d8d-9237-7d7e9b300009"))
                    .nome("2,4-D")
                    .tipo("HERBICIDA")
                    .build());

            Defensivo azoxistrobina = defensivoRep.save(Defensivo.builder()
                    .id(UUID.fromString("3b4f6aaf-f0f5-4f3c-bf3e-b6fca8100010"))
                    .nome("Azoxistrobina")
                    .tipo("FUNGICIDA")
                    .build());



            Plantio mandioca = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("4d9f5671-8371-4a29-8342-b292ffe2b939"))
                    .nome("Mandioca")
                    .tempMin(22)
                    .tempMax(34)
                    .aguaMM(40)
                    .mesesIdeais(Set.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER))
                    .urlImg("https://exemplo.com/mandioca.png")
                    .tiposSolo(Set.of(tp1))
                    .defensivos(Set.of(glifosato, mancozebe))
                    .build());

            Plantio amendoim = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("b03a3726-0b59-46dd-903c-05a0c1e713f7"))
                    .nome("Amendoim")
                    .tempMin(20)
                    .tempMax(32)
                    .aguaMM(35)
                    .mesesIdeais(Set.of(Month.SEPTEMBER, Month.OCTOBER))
                    .urlImg("https://exemplo.com/amendoim.png")
                    .tiposSolo(Set.of(tp1))
                    .defensivos(Set.of(atrazina, tebuconazol))
                    .build());

            Plantio melancia = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("cc6d8c2c-4560-4b71-a75f-9ce35a959dd6"))
                    .nome("Melancia")
                    .tempMin(20)
                    .tempMax(35)
                    .aguaMM(50)
                    .mesesIdeais(Set.of(Month.SEPTEMBER, Month.OCTOBER, Month.NOVEMBER))
                    .urlImg("https://exemplo.com/melancia.png")
                    .tiposSolo(Set.of(tp2))
                    .defensivos(Set.of(mancozebe, azoxistrobina))
                    .build());

            Plantio milho_verde = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("7831f3c7-51dc-4d7f-b459-1e3ed87814a9"))
                    .nome("Milho Verde")
                    .tempMin(18)
                    .tempMax(33)
                    .aguaMM(45)
                    .mesesIdeais(Set.of(Month.AUGUST, Month.SEPTEMBER, Month.OCTOBER))
                    .urlImg("https://exemplo.com/milho_verde.png")
                    .tiposSolo(Set.of(tp2))
                    .defensivos(Set.of(atrazina, clorpirifos))
                    .build());

            Plantio feijao = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("9c16ce2a-7316-41c1-9ca4-905d289b5372"))
                    .nome("Feijão")
                    .tempMin(18)
                    .tempMax(30)
                    .aguaMM(55)
                    .mesesIdeais(Set.of(Month.OCTOBER, Month.NOVEMBER))
                    .urlImg("https://exemplo.com/feijao.png")
                    .tiposSolo(Set.of(tp3))
                    .defensivos(Set.of(mancozebe, imidacloprido))
                    .build());

            Plantio sorgo = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("b3129aa5-028f-416c-8761-4d87143c846b"))
                    .nome("Sorgo")
                    .tempMin(20)
                    .tempMax(36)
                    .aguaMM(40)
                    .mesesIdeais(Set.of(Month.SEPTEMBER, Month.OCTOBER))
                    .urlImg("https://exemplo.com/sorgo.png")
                    .tiposSolo(Set.of(tp3))
                    .defensivos(Set.of(glifosato, atrazina))
                    .build());

            Plantio cafe = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("97abc9f5-ec88-44c3-85a8-a678c9d23973"))
                    .nome("Café")
                    .tempMin(18)
                    .tempMax(26)
                    .aguaMM(60)
                    .mesesIdeais(Set.of(Month.MARCH, Month.APRIL, Month.MAY))
                    .urlImg("https://exemplo.com/cafe.png")
                    .tiposSolo(Set.of(tp4))
                    .defensivos(Set.of(tebuconazol, azoxistrobina))
                    .build());

            Plantio cana_acucar = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("84485c05-3790-4c10-a47e-7032fc4a88ad"))
                    .nome("Cana-de-açúcar")
                    .tempMin(20)
                    .tempMax(35)
                    .aguaMM(70)
                    .mesesIdeais(Set.of(Month.SEPTEMBER, Month.OCTOBER))
                    .urlImg("https://exemplo.com/cana.png")
                    .tiposSolo(Set.of(tp4))
                    .defensivos(Set.of(glifosato, paraquate))
                    .build());

            Plantio alface = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("1ae97b15-6a2e-4675-b199-43759e8f4551"))
                    .nome("Alface")
                    .tempMin(15)
                    .tempMax(25)
                    .aguaMM(50)
                    .mesesIdeais(Set.of(Month.MARCH, Month.APRIL, Month.MAY))
                    .urlImg("https://exemplo.com/alface.png")
                    .tiposSolo(Set.of(tp5))
                    .defensivos(Set.of(imidacloprido, fipronil))
                    .build());

            Plantio tomate = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("30833675-f4a3-41bf-89ed-bc2d673ccb9a"))
                    .nome("Tomate")
                    .tempMin(18)
                    .tempMax(28)
                    .aguaMM(55)
                    .mesesIdeais(Set.of(Month.AUGUST, Month.SEPTEMBER))
                    .urlImg("https://exemplo.com/tomate.png")
                    .tiposSolo(Set.of(tp5))
                    .defensivos(Set.of(mancozebe, azoxistrobina))
                    .build());

            Plantio trigo = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("0d2e8c47-af6b-43fa-9358-17df42793b36"))
                    .nome("Trigo")
                    .tempMin(12)
                    .tempMax(24)
                    .aguaMM(45)
                    .mesesIdeais(Set.of(Month.MAY, Month.JUNE))
                    .urlImg("https://exemplo.com/trigo.png")
                    .tiposSolo(Set.of(tp6))
                    .defensivos(Set.of(tebuconazol, atrazina))
                    .build());

            Plantio cevada = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("06b2afcd-3fd0-42f7-9276-d77a46c87938"))
                    .nome("Cevada")
                    .tempMin(10)
                    .tempMax(22)
                    .aguaMM(40)
                    .mesesIdeais(Set.of(Month.MAY, Month.JUNE))
                    .urlImg("https://exemplo.com/cevada.png")
                    .tiposSolo(Set.of(tp6))
                    .defensivos(Set.of(tebuconazol, azoxistrobina))
                    .build());

            Plantio soja = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("06905ab5-fe14-4c50-883a-25e5a534bfe2"))
                    .nome("Soja")
                    .tempMin(18)
                    .tempMax(30)
                    .aguaMM(50)
                    .mesesIdeais(Set.of(Month.OCTOBER, Month.NOVEMBER))
                    .urlImg("https://exemplo.com/soja.png")
                    .tiposSolo(Set.of(tp8))
                    .defensivos(Set.of(glifosato, doisQuatroD))
                    .build());

            Plantio milho = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("e6de3d74-cccd-407f-a44f-5f276df623cd"))
                    .nome("Milho")
                    .tempMin(20)
                    .tempMax(35)
                    .aguaMM(45)
                    .mesesIdeais(Set.of(Month.SEPTEMBER, Month.OCTOBER))
                    .urlImg("https://exemplo.com/milho.png")
                    .tiposSolo(Set.of(tp8))
                    .defensivos(Set.of(atrazina, clorpirifos))
                    .build());

            Plantio arroz = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("e3ab1402-049e-4973-978a-bd7e75791759"))
                    .nome("Arroz")
                    .tempMin(20)
                    .tempMax(35)
                    .aguaMM(80)
                    .mesesIdeais(Set.of(Month.SEPTEMBER, Month.OCTOBER))
                    .urlImg("https://exemplo.com/arroz.png")
                    .tiposSolo(Set.of(tp11))
                    .defensivos(Set.of(paraquate, fipronil))
                    .build());

            Plantio feijao_preto = plantioRep.save(Plantio.builder()
                    .id(UUID.fromString("82ff98a3-82dd-4a74-a858-83cd8e1d9c2f"))
                    .nome("Feijão Preto")
                    .tempMin(18)
                    .tempMax(30)
                    .aguaMM(55)
                    .mesesIdeais(Set.of(Month.OCTOBER, Month.NOVEMBER))
                    .urlImg("https://exemplo.com/feijao_preto.png")
                    .tiposSolo(Set.of(tp11))
                    .defensivos(Set.of(mancozebe, imidacloprido))
                    .build());
        };

    }
}