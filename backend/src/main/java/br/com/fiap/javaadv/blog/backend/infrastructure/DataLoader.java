package br.com.fiap.javaadv.blog.backend.infrastructure;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.sql.Date;
import java.util.Set;
import java.util.UUID;

@Configuration
public class DataLoader {

    @Bean
    CommandLineRunner initDataTutPet() {
        return args -> {};
    }
}