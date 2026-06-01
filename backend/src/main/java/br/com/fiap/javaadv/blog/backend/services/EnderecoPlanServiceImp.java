package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.anticorruptionlayer.ViaCepService;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.EnderecoPlantioRepository;
import br.com.fiap.javaadv.blog.backend.datasource.repositories.UsuarioRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.EnderecoPlantio;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
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
    private final ViaCepService viaCepService;

    @Override
    public EnderecoPlantio create(EnderecoPlantio end){
        ViaCepResponse viaCep = viaCepService.buscarCep(end.getCep());

        if (viaCep == null) {
            throw new RuntimeException("CEP não encontrado.");
        }

        end.setLogradouro(viaCep.getLogradouro());
        end.setCidade(viaCep.getLocalidade());
        end.setEstado(viaCep.getUf());

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
