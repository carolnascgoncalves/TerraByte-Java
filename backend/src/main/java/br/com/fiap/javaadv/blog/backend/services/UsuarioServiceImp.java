package br.com.fiap.javaadv.blog.backend.services;

import br.com.fiap.javaadv.blog.backend.datasource.repositories.UsuarioRepository;
import br.com.fiap.javaadv.blog.backend.domainmodel.entities.Usuario;
import br.com.fiap.javaadv.blog.backend.services.interfaces.UsuarioService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.transaction.annotation.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;

import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional(propagation = Propagation.REQUIRED)
public class UsuarioServiceImp implements UsuarioService {
    private final UsuarioRepository usuarioRepository;

    @Override
    public Usuario create(Usuario usuario){
        return this.usuarioRepository.save(usuario);
    }

    @Override
    public Optional<Usuario> update(UUID id, Usuario patch) {
        return usuarioRepository.findById(id)
                .map(existing -> {
                    if (patch.getTelefone() != null)
                        existing.setTelefone(patch.getTelefone());

                    if (patch.getEmail() != null)
                        existing.setEmail(patch.getEmail());

                    if (patch.getSenha() != null)
                        existing.setSenha(patch.getSenha());

                    if (patch.getUrlImg() != null)
                        existing.setUrlImg(patch.getUrlImg());

                    return usuarioRepository.save(existing);
                });
    }
    @Override
    public void delete(UUID id){
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Optional<Usuario> fetchById(UUID id){
        return this.usuarioRepository.findById(id);
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public boolean existsById(UUID id){
        return this.usuarioRepository.existsById(id);
    }

    public Page<Usuario> fetchAll(Pageable pageable){
        return this.usuarioRepository.findAll(pageable);
    }

    @Override
    public Optional<Usuario> fetchByEmail(String email, String senha){
        Optional<Usuario> usu = usuarioRepository.findByEmail(email);
        if(usu.isPresent() && usu.get().getSenha().equals(senha)){return usu;}

        return Optional.empty();
    }
}
