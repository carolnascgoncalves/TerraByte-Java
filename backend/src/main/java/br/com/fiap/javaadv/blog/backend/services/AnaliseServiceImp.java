package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.AnalisePlantioRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.PlantioRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.AnalisePlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;
import br.com.fiap.javaadv.blog.backend.services.interfaces.AnaliseService;
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
public class AnaliseServiceImp implements AnaliseService {
    private final AnalisePlantioRepository analiseRepository;

    @Override
    public AnalisePlantio create(AnalisePlantio analise){
        return this.analiseRepository.save(analise);
    }

    @Override
    public void delete(UUID id){
        analiseRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<AnalisePlantio> fetchById(UUID id){
        return this.analiseRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.analiseRepository.existsById(id);
    }

    public Page<AnalisePlantio> fetchAll(Pageable pageable){
        return this.analiseRepository.findAll(pageable);
    }
}
