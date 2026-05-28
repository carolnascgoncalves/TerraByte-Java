package br.com.fiap.javaadv.blog.backend.services.interfaces;

import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AnalisePlantio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface AnaliseService {
    AnalisePlantio create(AnalisePlantio analise);

    Page<AnalisePlantio> fetchAll(Pageable pageable);

    Optional<AnalisePlantio> fetchById(UUID id);

    boolean existsById(UUID id);

    void delete(UUID id);
}
