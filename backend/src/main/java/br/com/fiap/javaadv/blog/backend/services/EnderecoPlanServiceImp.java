package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.anticorruptionlayer.ViaCepServiceImp;
import br.com.fiap.javaadv.blog.backend.anticorruptionlayer.interfaces.GeocodingService;
import br.com.fiap.javaadv.blog.backend.anticorruptionlayer.interfaces.SoilGridsService;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.EnderecoPlantioRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.TipoSoloRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.EnderecoPlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.TipoSolo;
import br.com.fiap.javaadv.blog.backend.domainmodel.enums.TipoSoloEnum;
import br.com.fiap.javaadv.blog.backend.resources.dtos.CoordenadaResponse;
import br.com.fiap.javaadv.blog.backend.resources.dtos.SoilGridsResultado;
import br.com.fiap.javaadv.blog.backend.resources.dtos.ViaCepResponse;
import br.com.fiap.javaadv.blog.backend.services.interfaces.EnderecoPlanService;
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
public class EnderecoPlanServiceImp implements EnderecoPlanService {
    private final EnderecoPlantioRepository enderecoRepository;
    private final ViaCepServiceImp viaCepServiceImp;
    private final GeocodingService geocodingService;
    private final SoilGridsService soilService;
    private final TipoSoloRepository tipoSoloRepository;


    @Override
    public EnderecoPlantio create(EnderecoPlantio end){
        ViaCepResponse viaCep = viaCepServiceImp.buscarCep(end.getCep());
        CoordenadaResponse coordenada = geocodingService.buscarCoordenadas(viaCep.getLocalidade(), viaCep.getEstado());

        double latitude = coordenada.getLatitude();
        double longitude = coordenada.getLongitude();

        if (viaCep.getLogradouro() == null) {
            throw new RuntimeException("CEP não encontrado.");
        }

        end.setLogradouro(viaCep.getLogradouro());
        end.setCidade(viaCep.getLocalidade());
        end.setEstado(viaCep.getEstado());

        end.setLatitude(latitude);
        end.setLongitude(longitude);

        SoilGridsResultado resultado = soilService.buscarTipoSolo(latitude, longitude);

        TipoSoloEnum tpSoloclassific = resultado.getTipoSolo();

        end.setRaioSoloKm(resultado.getRaioKm());

        end.setArgila(resultado.getSoilValues().getClay());
        end.setAreia(resultado.getSoilValues().getSand());
        end.setSilto(resultado.getSoilValues().getSilt());

        TipoSolo tipoSolo = tipoSoloRepository.findByNome(tpSoloclassific.name()).orElseThrow(() ->
                                new RuntimeException("Tipo de solo não encontrado: " + tpSoloclassific.name()));

        end.setTipoSolo(tipoSolo);

        return enderecoRepository.save(end);
    }


    @Override
    public Optional<EnderecoPlantio> update(UUID id, EnderecoPlantio patch) {
        return null;
        /*
        return enderecoRepository.findById(id)
                .map(existing -> {
                    if (patch.getTelefone() != null)
                        existing.setTelefone(patch.getTelefone());

                    if (patch.getEmail() != null)
                        existing.setEmail(patch.getEmail());

                    if (patch.getSenha() != null)
                        existing.setSenha(patch.getSenha());

                    if (patch.getUrlImg() != null)
                        existing.setUrlImg(patch.getUrlImg());

                    return enderecoRepository.save(existing);
                });

         */
    }



    @Override
    public void delete(UUID id){
        enderecoRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<EnderecoPlantio> fetchById(UUID id){
        return this.enderecoRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.enderecoRepository.existsById(id);
    }

    public Page<EnderecoPlantio> fetchAll(Pageable pageable){
        return this.enderecoRepository.findAll(pageable);
    }
}
