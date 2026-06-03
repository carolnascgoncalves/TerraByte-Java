package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.PlantioRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.TipoSoloRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TipoSolo;
import br.com.fiap.javaadv.blog.backend.services.interfaces.PlantioService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional( propagation = Propagation.REQUIRED)
public class PlantioServiceImp implements PlantioService {
    private final PlantioRepository plantioRepository;

    @Override
    public Plantio create(Plantio plantio){
        return this.plantioRepository.save(plantio);
    }

    @Override
    public void delete(UUID id){
        plantioRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<Plantio> fetchById(UUID id){
        return this.plantioRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.plantioRepository.existsById(id);
    }

    public Page<Plantio> fetchAll(Pageable pageable){
        return this.plantioRepository.findAll(pageable);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Plantio> fetchByTipoSolo(UUID tipoSoloId, Pageable pageable){
        return plantioRepository.findByTiposSolo_Id(tipoSoloId, pageable);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Plantio> fetchByDefensivo(UUID defensivoId, Pageable pageable){
        return plantioRepository.findByDefensivos_Id(defensivoId, pageable);
    }
}
