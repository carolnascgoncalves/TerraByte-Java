package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.DefensivoRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.PlantioRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Defensivo;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Plantio;
import br.com.fiap.javaadv.blog.backend.services.interfaces.DefensivoService;
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
public class DefensivoServiceImp implements DefensivoService {
    private final DefensivoRepository defensivoRepository;

    @Override
    public Defensivo create(Defensivo defensivo){
        return this.defensivoRepository.save(defensivo);
    }

    @Override
    public void delete(UUID id){
        defensivoRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<Defensivo> fetchById(UUID id){
        return this.defensivoRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.defensivoRepository.existsById(id);
    }

    public Page<Defensivo> fetchAll(Pageable pageable){
        return this.defensivoRepository.findAll(pageable);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Page<Defensivo> fetchByTipo(String tipo, Pageable pageable){
        return defensivoRepository.findByTipo(tipo, pageable);
    }
}
