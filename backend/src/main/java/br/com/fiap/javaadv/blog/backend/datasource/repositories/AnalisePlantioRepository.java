package br.com.fiap.javaadv.blog.backend.datasource.repositories;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AnalisePlantio;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AnalisePlantioRepository  extends JpaRepository<AnalisePlantio, UUID> {
}
