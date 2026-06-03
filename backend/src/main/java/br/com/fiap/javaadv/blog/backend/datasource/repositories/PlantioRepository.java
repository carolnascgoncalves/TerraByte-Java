package br.com.fiap.javaadv.blog.backend.datasource.repositories;


import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface PlantioRepository  extends JpaRepository<Plantio, UUID> {
    Page<Plantio> findByTiposSolo_Id(UUID tipoSoloId, Pageable pageable);

    Page<Plantio> findByDefensivos_Id(UUID defensivoId, Pageable pageable);
}
